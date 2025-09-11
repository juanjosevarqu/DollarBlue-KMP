package com.varqulabs.core.common.extensions

inline fun <T> tryOrDefault(defaultValue: T, blockToTry: () -> T): T = try {
    blockToTry()
} catch (_: Throwable) {
    defaultValue
}

