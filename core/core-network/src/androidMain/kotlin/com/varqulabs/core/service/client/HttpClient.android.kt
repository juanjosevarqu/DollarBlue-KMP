package com.varqulabs.core.service.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger
import okhttp3.OkHttpClient

actual fun platformHttpClientEngine(): HttpClientEngine {
    val okHttp = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .build()
    return OkHttp.create {
        preconfigured = okHttp
    }
}

actual val platformLogger: Logger
    get() = Logger.ANDROID