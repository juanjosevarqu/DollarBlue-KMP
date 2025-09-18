package com.varqulabs.feature.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.core.common.utils.DataState
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import com.varqulabs.dollarblue.core.ui.mvi.MVIContract
import com.varqulabs.dollarblue.core.ui.mvi.MVIDelegate
import com.varqulabs.core.common.extensions.roundDecimals
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import com.varqulabs.dollarblue.core.credits.domain.usecase.ConsumeCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.GetCredits
import com.varqulabs.feature.calculator.domain.model.DolarRate
import com.varqulabs.feature.calculator.domain.model.DollarType
import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import com.varqulabs.feature.calculator.domain.usecase.currency_conversion.SaveConversion
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.Init
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnPressButton
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnSaveConversion
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnSelectDolarRate
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnSwapCurrencies
import com.varqulabs.feature.calculator.presentation.CalculatorUIEffect.ConversionSavedSuccessfully
import com.varqulabs.feature.calculator.presentation.models.ButtonType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.roundToLong
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private const val MAX_ACCEPTABLE_VALUE = 999_999_999_999.99

class CalculatorViewModel(
    private val getBolivianUSDT: GetBolivianUSDT,
    private val getCredits: GetCredits,
    private val saveConversion: SaveConversion,
    private val consumeCredits: ConsumeCredits,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel(), MVIContract<CalculatorState, CalculatorEvent, CalculatorUIEffect> by MVIDelegate(CalculatorState()) {

    override fun eventHandler(event: CalculatorEvent) {
        when (event) {
            is Init -> getBolivianUSDTValues()
            is OnSaveConversion -> saveCurrencyConversion(event.name)
            is OnSwapCurrencies -> switchCurrencies()
            is OnPressButton -> onButtonPressed(event.character, event.buttonType)
            is OnSelectDolarRate -> onDolarRateSelected(event.newRate)
        }
    }

    private fun getBolivianUSDTValues() {
        viewModelScope.launch {
            getBolivianUSDT().collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isLoading = true)
                        is DataState.Success -> {
                            val usdtSellRate = DolarRate(DollarType.USDT_SELL, dataState.data.valueSell)
                            copy(
                                dollarRate = usdtSellRate,
                                outputValue = (dataState.data.valueSell * inputValue).roundDecimals(2),
                                dateUpdated = dataState.data.dateUpdated,
                                dollarRates = listOf(
                                    usdtSellRate,
                                    DolarRate(DollarType.USDT_BUY, (dataState.data.valueSell * 0.995).roundDecimals(2)),
                                    DolarRate(DollarType.OFFICIAL, 6.96),
                                ),
                                isLoading = false,
                                isError = false,
                            )
                        }
                        is DataState.Error -> copy(isError = true, isLoading = false)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun saveCurrencyConversion(name: String) {
        val conversion = CurrencyConversion(
            localId = 0L,
            conversionId = Uuid.random().toString(),
            dollarType = currentUiState.dollarRate.type.label,
            dollarRate = currentUiState.dollarRate.value,
            inputValue = currentUiState.inputValue,
            outputValue = currentUiState.outputValue,
            inputCurrency = currentUiState.inputCurrency,
            outputCurrency = currentUiState.outputCurrency,
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now(),
            name = name.ifEmpty { null }
        )
        viewModelScope.launch(dispatcher) {
            runCatching { saveConversion(conversion) }
                .onSuccess {
                    consumeCredits(1)
                    emitEffect(ConversionSavedSuccessfully)
                }
                .onFailure { updateUi { copy(isError = true) } }
        }
    }

    private fun onButtonPressed(
        character: String,
        buttonType: ButtonType,
    ) {
        when (buttonType) {
            ButtonType.OPERATOR -> appendOperator(character)
            ButtonType.NUMBER-> appendNumber(character)
            ButtonType.DELETE -> deleteLastCharacter()
            ButtonType.SWITCH -> switchCurrencies()
            ButtonType.CLEAR -> clearValues()
            ButtonType.SAVE -> validateIfUserCanSaveConversion()
        }
    }

    private fun validateIfUserCanSaveConversion() {
        viewModelScope.launch(dispatcher) {
            val credits = getCredits()
            if (credits <= 0) {
                emitEffect(CalculatorUIEffect.ShowWithoutCreditsDialog)
            } else {
                emitEffect(CalculatorUIEffect.ShowSaveConversionDialog)
            }
        }
    }

    private fun appendOperator(operator: String) {
        if (!currentUiState.inputExpression.endsWith('+')) {
            updateUi { copy(inputExpression = inputExpression + operator) }
        }
    }

    private fun appendNumber(number: String) {
        if (currentUiState.inputExpression.last() == '+' && number.startsWith('0')) {
            return
        }
        val newInputExpression = if (currentUiState.inputExpression == "0") { number } else {
            currentUiState.inputExpression + number
        }
        updateWithNewInputExpression(newInputExpression)
    }

    private fun deleteLastCharacter() {
        val newInputExpression = currentUiState.inputExpression.dropLast(1).ifEmpty { "0" }
        updateWithNewInputExpression(newInputExpression)
    }

    private fun updateWithNewInputExpression(newInputExpression: String) {
        val newInputValue = getCleanNumberFromInput(newInputExpression)
        if (newInputValue > MAX_ACCEPTABLE_VALUE) return
        val newOutputValue = calculateNewOutput(newInputValue)
        updateUi {
            copy(
                inputExpression = newInputExpression,
                inputValue = newInputValue.roundDecimals(2),
                outputValue = newOutputValue.roundDecimals(2),
            )
        }
    }

    private fun switchCurrencies() {
        updateUi {
            copy(
                inputExpression = outputValue.roundToLong().toString(),
                inputValue = outputValue,
                outputValue = inputValue,
                inputCurrency = outputCurrency,
                outputCurrency = inputCurrency,
            )
        }
    }

    private fun clearValues() {
        updateUi {
            copy(
                inputExpression = "0",
                inputValue = 0.0,
                outputValue = 0.0,
            )
        }
    }

    private fun getCleanNumberFromInput(input: String): Double {
        return input
            .split('+')
            .mapNotNull(String::toDoubleOrNull)
            .sum()
    }

    private fun onDolarRateSelected(newRate: DolarRate) {
        val newOutputValue = calculateNewOutput(bolivianValue = newRate.value)
        updateUi {
            copy(
                dollarRate = newRate,
                outputValue = newOutputValue.roundDecimals(2),
            )
        }
    }

    private fun calculateNewOutput(
        inputValue: Double = currentUiState.inputValue,
        bolivianValue: Double = currentUiState.dollarRate.value
    ): Double {
        return when (currentUiState.inputCurrency) {
            Currency.DOLLAR -> (inputValue * bolivianValue)
            Currency.BOLIVIANO -> (inputValue / bolivianValue)
        }
    }
}