package com.varqulabs.feature.calculator.core

/*
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToLong

fun Double.roundDecimals(decimals: Int): Double {
    val d = decimals.coerceIn(0, 15)
    return tryOrDefault(round(this * 10.0) / 10.0) {
        val factor = 10.0.pow(d).takeIf { it.isFinite() }
            ?: throw IllegalArgumentException("Factor inválido")
        val product = (this * factor).takeIf { it.isFinite() }
            ?: throw ArithmeticException("Producto inválido")
        round(product) / factor
    }
}

fun Double.formatWithoutDecimals(): String = "%,.0f".format(this)

fun Double.formatWithDecimals(decimals: Int = 2): String = "%,.${decimals}f".format(this)

fun Double.formatWithDecimalsBeforeRange(
    maxRangeShowingDecimals: Long = 1_000,
    decimals: Int = 1,
): String =
    if (this % 1.0 == 0.0 || this.roundToLong() >= maxRangeShowingDecimals) {
        this.formatWithoutDecimals()
    } else {
        this.formatWithDecimals(decimals)
    }

fun String.formatInputNumbers(decimals: Int = 0): String {
    val segments = this.split('+')
    val formatted = segments.map { segment ->
        val number = segment.toDoubleOrNull()
        if (number != null) {
            if (decimals == 0) {
                number.formatWithoutDecimals()
            } else {
                number.formatWithDecimalsBeforeRange()
            }
        } else {
            segment
        }
    }
    return formatted.joinToString(" + ")
}*/
