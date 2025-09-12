package com.varqulabs.dollarblue.history.presentation

import androidx.compose.runtime.Stable
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion

@Stable
data class HistoryState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val currencyConversions: List<CurrencyConversion> = emptyList(),
    val credits: Int = 0,
)
