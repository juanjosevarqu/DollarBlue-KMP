package com.varqulabs.feature.calculator.presentation

sealed class CalculatorUIEffect {

    data object ShowWithoutCreditsDialog : CalculatorUIEffect()

    data object ShowSaveConversionDialog : CalculatorUIEffect()

    data object ConversionSavedSuccessfully : CalculatorUIEffect()

}
