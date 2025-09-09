package com.varqulabs.feature.calculator.presentation

import androidx.compose.runtime.Stable
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import com.varqulabs.feature.calculator.domain.model.DolarRate
import com.varqulabs.feature.calculator.domain.model.DollarType

@Stable
data class CalculatorState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val inputExpression: String = "100",
    val inputValue: Double = 100.0,
    val outputValue: Double = 1650.0,
    val inputCurrency: Currency = Currency.DOLLAR,
    val outputCurrency: Currency = Currency.BOLIVIANO,
    val dollarRate: DolarRate = DolarRate(DollarType.USDT_SELL, 14.0),
    val dateUpdated: String = "",
    val dollarRates: List<DolarRate> = emptyList(),
)