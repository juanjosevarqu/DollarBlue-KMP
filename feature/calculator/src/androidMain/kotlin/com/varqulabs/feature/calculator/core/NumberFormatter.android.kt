package com.varqulabs.feature.calculator.core

import java.text.NumberFormat
import java.util.Locale

private fun toLocale(locale: AppLocale): Locale = when (locale) {
    AppLocale.System -> Locale.getDefault()
    AppLocale.EnUS   -> Locale.US
    AppLocale.EsBO   -> Locale("es", "BO")
}

actual object NumberFormatter {
    actual var locale: AppLocale = AppLocale.System
        set(value) {
            field = value
            tl.remove()
        }

    private val tl = object : ThreadLocal<NumberFormat>() {
        override fun initialValue(): NumberFormat =
            NumberFormat.getNumberInstance(toLocale(locale))
    }

    private fun nf(): NumberFormat =
             tl.get() ?: NumberFormat.getNumberInstance(toLocale(locale)).also { tl.set(it) }

    actual fun format(value: Double, minFraction: Int, maxFraction: Int, grouping: Boolean): String =
        nf().apply {
            isGroupingUsed = grouping
            minimumFractionDigits = minFraction
            maximumFractionDigits = maxFraction
        }.format(value)

    actual fun parse(text: String): Double? = runCatching { nf().parse(text)?.toDouble() }.getOrNull()
}