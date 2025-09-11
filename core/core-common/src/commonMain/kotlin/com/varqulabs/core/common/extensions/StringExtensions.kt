package com.varqulabs.core.common.extensions

import kotlin.text.iterator

fun String.normalizeLetters(): String {
    val out = StringBuilder(this.length)
    for (ch in this) {
        val mapped = when (ch) {
            'á','Á' -> 'a'
            'é','É' -> 'e'
            'í','Í' -> 'i'
            'ó','Ó' -> 'o'
            'ú','Ú' -> 'u'
            'ü','Ü' -> 'u'
            'ñ','Ñ' -> 'n'
            else -> ch.lowercaseChar()
        }
        out.append(mapped)
    }
    return out.toString()
}

fun String.normalizeForSearch(): String {
    return this.normalizeLetters()
        .replace("\\", "\\\\")
        .replace("%", "\\%")
        .replace("_", "\\_")
}

