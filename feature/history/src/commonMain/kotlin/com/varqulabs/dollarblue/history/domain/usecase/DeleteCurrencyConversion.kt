package com.varqulabs.dollarblue.history.domain.usecase

import com.varqulabs.dollarblue.core.conversions.domain.CurrencyConversionRepository

class DeleteCurrencyConversion(
    private val repository: CurrencyConversionRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteByLocalId(id)
    }
}