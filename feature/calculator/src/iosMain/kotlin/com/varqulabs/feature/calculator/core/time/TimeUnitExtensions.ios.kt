package com.varqulabs.feature.calculator.core.time

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSISO8601DateFormatWithFractionalSeconds
import platform.Foundation.NSISO8601DateFormatWithInternetDateTime
import platform.Foundation.NSISO8601DateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.systemTimeZone
import platform.Foundation.timeZoneWithAbbreviation
import platform.Foundation.timeZoneWithName
import kotlin.time.Instant

private fun nsTimeZone(id: String): NSTimeZone =
    if (id == "system") NSTimeZone.systemTimeZone else NSTimeZone.timeZoneWithName(id)!!

private fun nsLocaleForPattern(pattern: String): NSLocale =
    if (pattern.contains('a')) NSLocale(localeIdentifier = "en_US_POSIX") else NSLocale.currentLocale()

private fun dateFormatter(pattern: String, timeZoneId: String): NSDateFormatter =
    NSDateFormatter().apply {
        dateFormat = pattern
        locale = nsLocaleForPattern(pattern)
        timeZone = nsTimeZone(timeZoneId)
    }

actual fun String.formatDateCustom(
    inputPattern: String,
    outputPattern: String
): String {
    val iso = NSISO8601DateFormatter().apply {
        formatOptions =
            NSISO8601DateFormatWithInternetDateTime or NSISO8601DateFormatWithFractionalSeconds
        timeZone = NSTimeZone.timeZoneWithAbbreviation("UTC")!!
    }
    val isoDate = iso.dateFromString(this)
    if (isoDate != null) return dateFormatter(outputPattern, "system").stringFromDate(isoDate)

    val parsed = dateFormatter(inputPattern, "UTC").dateFromString(this) ?: return this
    return dateFormatter(outputPattern, "system").stringFromDate(parsed)
}

actual fun Instant.toFormattedDate(
    pattern: DatePattern,
    timeZoneId: String
): String {
    val df = dateFormatter(pattern.pattern, timeZoneId)
    val seconds = toEpochMilliseconds().toDouble() / 1000.0
    val date = NSDate.dateWithTimeIntervalSince1970(seconds)
    return df.stringFromDate(date)
}
