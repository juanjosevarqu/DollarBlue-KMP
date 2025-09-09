package com.varqulabs.feature.calculator.core

enum class AppLocale { System, EnUS, EsBO }

expect object NumberFormatter {
    var locale: AppLocale

    fun format(
        value: Double,
        minFractionDigits: Int = 0,
        maxFractionDigits: Int = 0,
        groupingEnabled: Boolean = true,
    ): String

    fun parse(input: String): Double?
}