package com.varqulabs.feature.calculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.varqulabs.core.common.extensions.roundDecimals
import com.varqulabs.feature.calculator.domain.model.DolarRate
import com.varqulabs.feature.calculator.domain.model.DollarType
import com.varqulabs.feature.calculator.presentation.components.rates.DolarRateSelector
import com.varqulabs.feature.calculator.presentation.models.ButtonType
import dollarbluekmp.feature.calculator.generated.resources.Res
import dollarbluekmp.feature.calculator.generated.resources.button_delete
import dollarbluekmp.feature.calculator.generated.resources.button_number
import dollarbluekmp.feature.calculator.generated.resources.current_input
import org.jetbrains.compose.resources.stringResource
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class CalculatorScreenTest {

    @Test
    fun dollarRateSelectorDropdownMenuWorksWell() = runComposeUiTest {
        val rates = listOf(
                DolarRate(DollarType.USDT_SELL, 14.0),
                DolarRate(DollarType.USDT_BUY, (14.0 * 0.995).roundDecimals(2)),
                DolarRate(DollarType.OFFICIAL, 6.96),
            )

        setContent {
            DolarRateSelector(
                rates = rates,
                onSelectRate = {}
            )
        }

        onNodeWithText("Dólar USDT (Venta)").performClick()
        onNodeWithText("Dólar Oficial").assertExists()
        onNodeWithText("Dólar USDT (Compra)").assertExists()

        onNodeWithText("Dólar Oficial").performClick()
        onNodeWithText("Dólar USDT (Venta)").assertDoesNotExist()
        onNodeWithText("Dólar USDT (Compra)").assertDoesNotExist()
        onNodeWithText("Dólar Oficial").assertIsDisplayed()
    }

    @Test
    fun dollarRateSelectorUpdatesCurrencyOutputAndRateNow() = runComposeUiTest {
        val rates = listOf(
            DolarRate(DollarType.USDT_SELL, 14.0),
            DolarRate(DollarType.OFFICIAL, 6.96),
        )

        var state by mutableStateOf(
            CalculatorState(
                dollarRates = rates,
                dollarRate = rates.first(),
            )
        )

        setContent {
            CalculatorScreen(
                state = state,
                eventHandler = { event ->
                    if (event is CalculatorEvent.OnSelectDolarRate) {
                        state = state.copy(
                            dollarRate = event.newRate,
                            outputValue = state.inputValue * event.newRate.value
                        )
                    }
                },
                goToHistory = {},
            )
        }

        onNodeWithText("1 USDT ≈ 14.00 BOB").assertIsDisplayed()
        onNodeWithText("1,400").assertIsDisplayed()

        onNodeWithText("Dólar USDT (Venta)").performClick()
        onNodeWithText("Dólar Oficial").performClick()

        onNodeWithText("1 USDT ≈ 6.96 BOB").assertIsDisplayed()
        onNodeWithText("696").assertIsDisplayed()
    }

    @Test
    fun calculatorNumberButtonsUpdatesInput() = runComposeUiTest {
        lateinit var number8: String

        var state by mutableStateOf(
            CalculatorState(
                inputExpression = "0",
                inputValue = 0.0,
                outputValue = 0.0,
            )
        )

        setContent {
            number8 = stringResource(Res.string.button_number, "8")
            CalculatorScreen(
                state = state,
                eventHandler = { event ->
                    if (event is CalculatorEvent.OnPressButton) {
                        state = state.copy(
                            inputExpression = if (state.inputExpression == "0") {
                                event.character
                            } else {
                                state.inputExpression + event.character
                            }
                        )
                    }
                },
                goToHistory = {}
            )
        }

        onNodeWithContentDescription(number8).performClick()
        onNodeWithText("5").performClick()
        onNodeWithText("9").performClick()
        onNodeWithText("5").performClick()
        onNodeWithText("9").performClick()
        onNodeWithText("3").performClick()
        onNodeWithText("00").performClick()
        onNodeWithText("2").performClick()

        onNodeWithText("859,593,002").assertIsDisplayed()
    }

    @Test
    fun calculatorDeleteButtonWorksWell() = runComposeUiTest {
        lateinit var number5: String
        lateinit var number8: String
        lateinit var delete: String
        lateinit var input: String
        var state by mutableStateOf(
            CalculatorState(
                inputExpression = "0",
                inputValue = 0.0,
                outputValue = 0.0,
            )
        )

        setContent {
            number5 = stringResource(Res.string.button_number, "5")
            number8 = stringResource(Res.string.button_number, "8")
            delete = stringResource(Res.string.button_delete)
            input = stringResource(Res.string.current_input)
            CalculatorScreen(
                state = state,
                eventHandler = { event ->
                    when (event) {
                        CalculatorEvent.OnPressButton("D", ButtonType.DELETE) -> {
                            state = state.copy(inputExpression = state.inputExpression.dropLast(1))
                        }
                        is CalculatorEvent.OnPressButton -> {
                            state = state.copy(inputExpression = state.inputExpression + event.character)
                        }
                    }
                },
                goToHistory = {}
            )
        }

        onNodeWithContentDescription(number5).performClick()
        onNodeWithContentDescription(number5).performClick()
        onNodeWithContentDescription(number8).performClick()
        onNodeWithContentDescription(number5).performClick()
        onNodeWithContentDescription(delete).performClick()
        onNodeWithContentDescription(input).assert(hasText("558"))
        onNodeWithContentDescription(delete).performClick()
        onNodeWithContentDescription(input).assert(hasText("55"))
    }



}