package com.varqulabs.feature.calculator.core

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import platform.Foundation.currentLocale
import platform.Foundation.numberWithDouble
import platform.Foundation.NSLocale as AppleNSLocale

private fun toNSLocale(locale: AppLocale): NSLocale = when (locale) {
    AppLocale.System -> AppleNSLocale.currentLocale()
    AppLocale.EnUS   -> AppleNSLocale(localeIdentifier = "en_US")
    AppLocale.EsBO   -> AppleNSLocale(localeIdentifier = "es_BO")
}

actual object NumberFormatter {
    actual var locale: AppLocale = AppLocale.System

    actual fun format(value: Double, minFraction: Int, maxFraction: Int, grouping: Boolean): String {
        val appLoc = this.locale
        val nf = NSNumberFormatter().apply {
            numberStyle = NSNumberFormatterDecimalStyle
            this.locale = toNSLocale(appLoc)
            usesGroupingSeparator = grouping
            minimumFractionDigits = minFraction.toULong()
            maximumFractionDigits = maxFraction.toULong()
            allowsFloats = true
        }
        return nf.stringFromNumber(NSNumber.numberWithDouble(value)) ?: value.toString()
    }

    actual fun parse(text: String): Double? {
        val appLoc = this.locale
        val nf = NSNumberFormatter().apply {
            numberStyle = NSNumberFormatterDecimalStyle
            this.locale = toNSLocale(appLoc)
            allowsFloats = true
        }
        return nf.numberFromString(text)?.doubleValue
    }
}