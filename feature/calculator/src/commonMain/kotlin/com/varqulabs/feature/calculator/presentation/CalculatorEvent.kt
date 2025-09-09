package com.varqulabs.feature.calculator.presentation

import com.varqulabs.feature.calculator.domain.model.DolarRate
import com.varqulabs.feature.calculator.presentation.models.ButtonType

interface CalculatorEvent {

    data object Init : CalculatorEvent

    data object OnSwapCurrencies : CalculatorEvent

    data class OnPressButton(
        val character: String,
        val buttonType: ButtonType,
    ) : CalculatorEvent

    data class OnSelectDolarRate(
        val newRate: DolarRate,
    ) : CalculatorEvent

    data class OnSaveConversion(
        val name: String,
    ) : CalculatorEvent

}