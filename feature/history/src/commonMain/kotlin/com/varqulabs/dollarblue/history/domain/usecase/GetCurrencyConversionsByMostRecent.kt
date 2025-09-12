package com.varqulabs.dollarblue.history.domain.usecase

import com.varqulabs.dollarblue.core.conversions.domain.CurrencyConversionRepository
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import kotlinx.coroutines.flow.Flow

class GetCurrencyConversionsByMostRecent(
    private val repository: CurrencyConversionRepository
) {
    operator fun invoke(): Flow<List<CurrencyConversion>> {
        return repository.getAllSortedByMostRecent()
    }
}