package com.varqulabs.feature.calculator.core

inline fun <T> tryOrDefault(defaultValue: T, blockToTry: () -> T): T = try {
    blockToTry()
} catch (_: Throwable) {
    defaultValue
}

