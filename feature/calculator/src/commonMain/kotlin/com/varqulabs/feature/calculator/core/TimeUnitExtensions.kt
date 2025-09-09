package com.varqulabs.feature.calculator.core

/*import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlinx.datetime.TimeZone as KotlinTimeZone

fun String.formatDateCustom(
    inputPattern: String = DatePattern.DEFAULT_ISO.pattern,
    outputPattern: String = DatePattern.DAY_MONTH_YEAR_HYPHEN_HOUR_AMPM.pattern,
): String {
    return try {
        val inputFormat: DateFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat: DateFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()
        val newDate: Date = inputFormat.parse(this)
        outputFormat.format(newDate)
    } catch (e: ParseException) {
        this
    }
}

@OptIn(FormatStringsInDatetimeFormats::class)
fun Instant.toFormattedDate(
    pattern: DatePattern = DatePattern.DEFAULT_ISO,
    timeZone: KotlinTimeZone = KotlinTimeZone.currentSystemDefault(),
): String {
    val ldt = toLocalDateTime(timeZone)
    val fmt = LocalDateTime.Format { byUnicodePattern(pattern.pattern) }
    return ldt.format(fmt)
}*/

enum class DatePattern(val pattern: String) {
    DEFAULT_ISO("yyyy-MM-dd'T'HH:mm:ss"),
    DAY_MONTH_YEAR_HYPHEN_HOUR_AMPM("dd/MM/yyyy - hh:mm a"),
    DAY_MONTH_YEAR_HYPHEN_HOUR("dd/MM/yyyy - HH:mm"),
    HOUR_DAY_OF_MONTH("HH:mm '·' d 'de' MMM")
}
