package com.varqulabs.core.service.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.DEFAULT

actual fun platformHttpClientEngine(): HttpClientEngine {
    return Darwin.create()
}

actual val platformLogger: Logger
    get() = Logger.DEFAULT