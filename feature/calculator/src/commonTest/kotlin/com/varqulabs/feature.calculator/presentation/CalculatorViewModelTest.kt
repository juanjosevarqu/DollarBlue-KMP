package com.varqulabs.feature.calculator.presentation

import app.cash.turbine.test
import com.varqulabs.core.common.extensions.roundDecimals
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import com.varqulabs.dollarblue.core.credits.domain.usecase.ConsumeCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.GetCredits
import com.varqulabs.feature.calculator.data.repository.FakeBolivianUSDTRepository
import com.varqulabs.feature.calculator.domain.Constants
import com.varqulabs.feature.calculator.domain.model.BolivianUSDT
import com.varqulabs.feature.calculator.domain.model.DolarRate
import com.varqulabs.feature.calculator.domain.model.DollarType
import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import com.varqulabs.feature.calculator.domain.usecase.currency_conversion.SaveConversion
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.Init
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnPressButton
import com.varqulabs.feature.calculator.presentation.models.ButtonType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CalculatorViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var fakeBolivianUSDTRepository: FakeBolivianUSDTRepository
    private lateinit var fakeCreditsRepository: FakeCreditsRepository
    private lateinit var fakeCurrencyConversionRepository: FakeCurrencyConversionRepository

    private lateinit var getBolivianUSDT: GetBolivianUSDT
    private lateinit var getCredits: GetCredits
    private lateinit var saveConversion: SaveConversion
    private lateinit var consumeCredits: ConsumeCredits

    private lateinit var viewModel: CalculatorViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        fakeBolivianUSDTRepository = FakeBolivianUSDTRepository()
        fakeCreditsRepository = FakeCreditsRepository()
        fakeCurrencyConversionRepository = FakeCurrencyConversionRepository()

        getBolivianUSDT = GetBolivianUSDT(fakeBolivianUSDTRepository)
        getCredits = GetCredits(fakeCreditsRepository)
        saveConversion = SaveConversion(fakeCurrencyConversionRepository)
        consumeCredits = ConsumeCredits(fakeCreditsRepository)

        viewModel = CalculatorViewModel(
            getBolivianUSDT = getBolivianUSDT,
            getCredits = getCredits,
            saveConversion = saveConversion,
            consumeCredits = consumeCredits,
            dispatcher = testDispatcher,
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun given_viewmodel_when_started_then_state_is_default() {
        val state = viewModel.currentUiState
        assertEquals(
            CalculatorState(
                inputExpression = "100",
                inputValue = 100.0,
                outputValue = 1400.0,
                inputCurrency = Currency.DOLLAR,
                outputCurrency = Currency.BOLIVIANO,
                dollarRate = DolarRate(DollarType.USDT_SELL, 14.0),
                dateUpdated = "",
            ),
            state
        )
    }

    @Test
    fun given_usdt_api_throws_error_when_init_event_then_state_is_error_and_loading_is_false() = runTest {
        fakeBolivianUSDTRepository.offlineRate = BolivianUSDT()
        fakeBolivianUSDTRepository.throwApiError = true

        viewModel.eventHandler(Init)
        advanceUntilIdle()

        val finalState = viewModel.currentUiState
        assertTrue(finalState.isError)
        assertFalse(finalState.isLoading)
    }

    @Test
    fun given_repo_only_returns_offline_rate_when_init_event_then_state_updates_with_offline_rate() = runTest {
        val offlineRate = BolivianUSDT(
            valueSell = 17.3,
            dateUpdated = "2025-10-01T12:00:00Z"
        )
        fakeBolivianUSDTRepository.offlineRate = offlineRate

        viewModel.eventHandler(Init)
        advanceUntilIdle()

        val finalState = viewModel.currentUiState

        assertEquals(offlineRate.valueSell, finalState.dollarRate.value)
        assertEquals((offlineRate.valueSell * finalState.inputValue).roundDecimals(2), finalState.outputValue)
        assertEquals(offlineRate.dateUpdated, finalState.dateUpdated)

        assertEquals(DolarRate(DollarType.USDT_SELL, offlineRate.valueSell), finalState.dollarRates[0])
        assertEquals(DolarRate(DollarType.USDT_BUY, (offlineRate.valueSell * 0.995).roundDecimals(2)), finalState.dollarRates[1])
        assertEquals(DolarRate(DollarType.OFFICIAL, 6.96), finalState.dollarRates[2])
        assertFalse(finalState.isLoading)
    }

    @Test
    fun given_repo_returns_offline_and_online_rate_when_init_event_then_state_prefers_online_rate_over_offline() = runTest {
        val offlineRate = BolivianUSDT(
            valueSell = 17.3,
            dateUpdated = "2025-10-01T11:00:00Z"
        )
        val onlineRate = BolivianUSDT(
            valueSell = 17.5,
            dateUpdated = "2025-10-01T12:00:00Z"
        )
        fakeBolivianUSDTRepository.offlineRate = offlineRate
        fakeBolivianUSDTRepository.onlineRate = onlineRate

        viewModel.eventHandler(Init)
        advanceUntilIdle()

        val finalState = viewModel.currentUiState
        assertEquals(onlineRate.valueSell, finalState.dollarRate.value)
        assertEquals((onlineRate.valueSell * finalState.inputValue).roundDecimals(2), finalState.outputValue)
        assertEquals(onlineRate.dateUpdated, finalState.dateUpdated)
        assertFalse(finalState.isLoading)
    }

    @Test
    fun given_initial_state_when_user_swaps_currencies_then_state_updates_correctly() {
        val beforeState = viewModel.currentUiState
        assertEquals(Currency.DOLLAR, beforeState.inputCurrency)
        assertEquals(Currency.BOLIVIANO, beforeState.outputCurrency)

        viewModel.eventHandler(CalculatorEvent.OnSwapCurrencies)

        val afterState = viewModel.currentUiState
        assertEquals(Currency.BOLIVIANO, afterState.inputCurrency)
        assertEquals(Currency.DOLLAR, afterState.outputCurrency)
        assertEquals(beforeState.outputValue, afterState.inputValue)
        assertEquals(beforeState.inputValue, afterState.outputValue)
    }

    @Test
    fun given_initial_state_when_user_presses_numbers_then_input_updates_correctly() {
        viewModel.eventHandler(OnPressButton("7", ButtonType.NUMBER))
        val state = viewModel.currentUiState
        assertEquals("1007", state.inputExpression)
        assertEquals(1007.0, state.inputValue)

        viewModel.eventHandler(OnPressButton("0", ButtonType.NUMBER))
        val afterState = viewModel.currentUiState
        assertEquals("10070", afterState.inputExpression)
        assertEquals(10070.0, afterState.inputValue)
    }

    @Test
    fun given_input_ends_with_operator_when_user_presses_operator_again_then_input_does_not_append_another_operator() {
        viewModel.eventHandler(OnPressButton("+", ButtonType.OPERATOR))
        assertTrue(viewModel.currentUiState.inputExpression.endsWith("+"))

        viewModel.eventHandler(OnPressButton("+", ButtonType.OPERATOR))
        assertFalse(viewModel.currentUiState.inputExpression.endsWith("++"))
    }

    @Test
    fun given_user_with_credits_when_user_presses_save_then_emit_show_save_conversion() = runTest {
        viewModel.uiEffect.test {
            viewModel.eventHandler(OnPressButton("Save", ButtonType.SAVE))
            assertEquals(CalculatorUIEffect.ShowSaveConversionDialog, awaitItem())
            expectNoEvents()
        }
    }

    @Test
    fun given_user_has_no_credits_when_user_presses_save_then_emit_without_credits() = runTest {
        fakeCreditsRepository.hasEnoughCredits = false
        viewModel.uiEffect.test {
            viewModel.eventHandler(OnPressButton("Save", ButtonType.SAVE))
            assertEquals(CalculatorUIEffect.ShowWithoutCreditsDialog, awaitItem())
            expectNoEvents()
        }
    }

    @Test
    fun given_initial_dollar_rate_when_user_selects_new_rate_then_state_updates_correctly() = runTest {
        fakeBolivianUSDTRepository.offlineRate = BolivianUSDT(
            valueSell = 17.3,
            dateUpdated = "2025-10-01T12:00:00Z"
        )
        viewModel.eventHandler(Init)
        advanceUntilIdle()

        viewModel.eventHandler(CalculatorEvent.OnSelectDolarRate(DolarRate(DollarType.USDT_BUY)))
        assertEquals(DolarRate(DollarType.USDT_BUY), viewModel.currentUiState.dollarRate)

        viewModel.eventHandler(CalculatorEvent.OnSelectDolarRate(DolarRate(DollarType.OFFICIAL)))
        assertEquals(DolarRate(DollarType.OFFICIAL), viewModel.currentUiState.dollarRate)
    }

    @Test
    fun given_bigger_input_near_limit_when_user_presses_another_number_then_input_does_not_allow_more_digits() {
        viewModel.updateUi {
            copy(
                inputExpression = Constants.MAX_ACCEPTABLE_VALUE.toString(),
                inputValue = Constants.MAX_ACCEPTABLE_VALUE
            )
        }
        viewModel.eventHandler(OnPressButton("1", ButtonType.NUMBER))
        assertEquals(Constants.MAX_ACCEPTABLE_VALUE, viewModel.currentUiState.inputValue)

        viewModel.eventHandler(OnPressButton("0", ButtonType.NUMBER))
        assertEquals(Constants.MAX_ACCEPTABLE_VALUE, viewModel.currentUiState.inputValue)
    }

    @Test
    fun given_input_ends_with_operator_when_user_presses_zero_then_zero_is_not_appended() {
        viewModel.eventHandler(OnPressButton("+", ButtonType.OPERATOR))
        viewModel.eventHandler(OnPressButton("0", ButtonType.NUMBER))

        val state = viewModel.currentUiState
        assertFalse(state.inputExpression.endsWith('0'))
    }

    @Test
    fun given_some_input_when_user_presses_clear_then_state_resets() {
        viewModel.eventHandler(OnPressButton("7", ButtonType.NUMBER))
        viewModel.eventHandler(OnPressButton("Clear", ButtonType.CLEAR))

        val state = viewModel.currentUiState
        assertEquals("0", state.inputExpression)
        assertEquals(0.0, state.inputValue)
        assertEquals(0.0, state.outputValue)
    }

}