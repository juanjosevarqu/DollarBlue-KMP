package com.varqulabs.dollarblue.core.conversions.domain.model

import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionEntity
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class CurrencyConversion(
    val localId: Long,
    val conversionId: String,
    val dollarType: String,
    val dollarRate: Double,
    val inputValue: Double,
    val outputValue: Double,
    val inputCurrency: Currency,
    val outputCurrency: Currency,
    val createdAt: Instant,
    val updatedAt: Instant,
    val name: String? = null,
)

@OptIn(ExperimentalTime::class)
fun CurrencyConversion.mapToEntity(): CurrencyConversionEntity =
    CurrencyConversionEntity(
        localId = localId,
        conversionId = conversionId,
        dollarType = dollarType,
        dollarRate = dollarRate,
        inputValue = inputValue,
        outputValue = outputValue,
        inputCurrency = inputCurrency.code,
        outputCurrency = outputCurrency.code,
        createdAt = createdAt,
        updatedAt = updatedAt,
        name = name,
        normalizedName = name
    )