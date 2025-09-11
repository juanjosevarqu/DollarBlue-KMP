package com.varqulabs.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val platformDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default