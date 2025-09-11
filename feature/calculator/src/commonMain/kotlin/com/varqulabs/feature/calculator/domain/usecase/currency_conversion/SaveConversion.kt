package com.varqulabs.feature.calculator.domain.usecase.currency_conversion

import com.varqulabs.dollarblue.core.conversions.domain.CurrencyConversionRepository
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion

class SaveConversion(
    private val repository: CurrencyConversionRepository,
) {
    suspend operator fun invoke(conversion: CurrencyConversion) {
        repository.insert(conversion)
    }
}