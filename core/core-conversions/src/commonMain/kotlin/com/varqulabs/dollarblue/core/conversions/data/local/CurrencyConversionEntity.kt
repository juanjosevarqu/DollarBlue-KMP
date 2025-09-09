package com.varqulabs.dollarblue.core.conversions.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency.Companion.fromCode
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Entity(tableName = "currency_conversions")
@OptIn(ExperimentalTime::class)
data class CurrencyConversionEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0L,
    val conversionId: String,
    val dollarType: String,
    val dollarRate: Double,
    val inputValue: Double,
    val outputValue: Double,
    val inputCurrency: String,
    val outputCurrency: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val name: String? = null,
    val normalizedName: String? = null,
)

@OptIn(ExperimentalTime::class)
fun CurrencyConversionEntity.mapToDomain(): CurrencyConversion =
    CurrencyConversion(
        localId = localId,
        conversionId = conversionId,
        dollarType = dollarType,
        dollarRate = dollarRate,
        inputValue = inputValue,
        outputValue = outputValue,
        inputCurrency = fromCode(inputCurrency),
        outputCurrency = fromCode(outputCurrency),
        createdAt = createdAt,
        updatedAt = updatedAt,
        name = name
    )