package com.varqulabs.feature.calculator.core.time

enum class DatePattern(val pattern: String) {
    DEFAULT_ISO("yyyy-MM-dd'T'HH:mm:ss"),
    DAY_MONTH_YEAR_HYPHEN_HOUR_AMPM("dd/MM/yyyy - hh:mm a"),
    DAY_MONTH_YEAR_HYPHEN_HOUR("dd/MM/yyyy - HH:mm"),
    HOUR_DAY_OF_MONTH("HH:mm '·' d 'de' MMM")
}