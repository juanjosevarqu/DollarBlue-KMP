package com.varqulabs.feature.calculator.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val platformDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default