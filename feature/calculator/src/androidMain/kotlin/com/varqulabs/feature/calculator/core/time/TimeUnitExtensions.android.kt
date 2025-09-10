package com.varqulabs.feature.calculator.core.time

import kotlin.time.Instant
import java.time.Instant as JInstant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private fun zoneId(id: String) =
    if (id == "system") ZoneId.systemDefault() else ZoneId.of(id)

private fun formatter(pattern: String) = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())

actual fun String.formatDateCustom(
    inputPattern: String,
    outputPattern: String
): String = runCatching {
    val output = formatter(outputPattern).withZone(ZoneId.systemDefault())
    val instant = runCatching { JInstant.parse(this) }.getOrNull()
        ?: LocalDateTime.parse(this, formatter(inputPattern))
            .atZone(ZoneId.of("UTC"))
            .toInstant()
    output.format(instant)
}.getOrElse { this }

actual fun Instant.toFormattedDate(
    pattern: DatePattern,
    timeZoneId: String
): String {
    val zoned = ZonedDateTime.ofInstant(JInstant.ofEpochMilli(toEpochMilliseconds()), zoneId(timeZoneId))
    return formatter(pattern.pattern).format(zoned)
}