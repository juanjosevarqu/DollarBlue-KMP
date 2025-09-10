package com.varqulabs.feature.calculator.core.time

import kotlin.time.Instant

expect fun String.formatDateCustom(
    inputPattern: String = DatePattern.DEFAULT_ISO.pattern,
    outputPattern: String = DatePattern.DAY_MONTH_YEAR_HYPHEN_HOUR_AMPM.pattern,
): String

expect fun Instant.toFormattedDate(
    pattern: DatePattern = DatePattern.DEFAULT_ISO,
    timeZoneId: String = "system",
): String
