package com.varqulabs.feature.calculator.domain.usecase.currency_conversion

import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import com.varqulabs.dollarblue.core.credits.domain.usecase.ConsumeCredits

class SaveConversionAndConsumeCredits(
    private val saveConversion: SaveConversion,
    private val consumeCredits: ConsumeCredits
) {
    suspend operator fun invoke(conversion: CurrencyConversion) {
        saveConversion(conversion)
        consumeCredits(1)
    }
}