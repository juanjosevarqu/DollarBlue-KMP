package com.varqulabs.core.common.formatter

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

    actual fun format(value: Double, minFractionDigits: Int, maxFractionDigits: Int, groupingEnabled: Boolean): String =
        nf().apply {
            isGroupingUsed = groupingEnabled
            minimumFractionDigits = minFractionDigits
            maximumFractionDigits = maxFractionDigits
        }.format(value)

    actual fun parse(input: String): Double? = runCatching { nf().parse(input)?.toDouble() }.getOrNull()
}