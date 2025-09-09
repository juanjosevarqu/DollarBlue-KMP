package com.varqulabs.core.service.di

import com.varqulabs.core.service.network.ApiConfig
import com.varqulabs.core.service.network.createHttpClient
import com.varqulabs.core.service.utils.ServiceKey
import com.varqulabs.core.service.utils.qualifierOf
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import org.koin.dsl.onClose

fun serviceModule(
    baseUrl: String,
): Module = module {
    single { ApiConfig(baseUrl = baseUrl) }
    single { createHttpClient(get()) } onClose { client -> client?.close() }
}

fun serviceModule(
    baseUrl: String,
    stgUrl: String? = null,
    qualifier: Qualifier? = null,
) = module {
    single(qualifier) {
        val env = getKoin().getProperty<String>("env")?.lowercase() ?: "prod"
        val finalUrl = if (env == "staging" && stgUrl != null) stgUrl else baseUrl
        ApiConfig(baseUrl = finalUrl)
    }

    single<HttpClient>(qualifier = qualifier) {
        createHttpClient(get(qualifier))
    } onClose { client -> client?.close() }
}

fun serviceModule(
    key: ServiceKey,
    baseUrl: String,
    stgUrl: String? = null,
) = serviceModule(
    baseUrl = baseUrl,
    stgUrl = stgUrl,
    qualifier = qualifierOf(key)
)