package com.varqulabs.feature.calculator.core

import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToLong

fun Double.roundDecimals(fractionDigits: Int): Double {
    val clamped = fractionDigits.coerceIn(0, 15)
    val factor = 10.0.pow(clamped)
    return if (factor.isFinite()) round(this * factor) / factor else this
}

fun Double.formatWithoutDecimals(): String =
    NumberFormatter.format(
        value = this,
        minFractionDigits = 0,
        maxFractionDigits = 0,
        groupingEnabled = true
    )

fun Double.formatWithDecimals(fractionDigits: Int = 2): String =
    NumberFormatter.format(
        value = this,
        minFractionDigits = fractionDigits,
        maxFractionDigits = fractionDigits,
        groupingEnabled = true
    )

fun Double.formatWithDecimalsBeforeRange(
    maxRangeShowingDecimals: Long = 1_000,
    decimals: Int = 1,
): String =
    if (this % 1.0 == 0.0 || this.roundToLong() >= maxRangeShowingDecimals) {
        this.formatWithoutDecimals()
    } else {
        this.formatWithDecimals(decimals)
    }

fun String.formatInputNumbers(fractionDigits: Int = 0): String =
    split('+').joinToString(" + ") { termRaw ->
        val term = termRaw.trim()
        val parsed = NumberFormatter.parse(term) ?: term.toDoubleOrNull()
        if (parsed != null) {
            if (fractionDigits == 0) parsed.formatWithoutDecimals()
            else parsed.formatWithDecimalsBeforeRange()
        } else term
    }
