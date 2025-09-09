package com.varqulabs.feature.calculator.data.remote

import com.varqulabs.feature.calculator.data.remote.dto.BolivianUSDTDto
import com.varqulabs.feature.calculator.domain.service.BolivianUSDTService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class BolivianUSDTServiceImpl(
    private val httpClient: HttpClient,
) : BolivianUSDTService {

    override suspend fun getBolivianUSDTBinance(): BolivianUSDTDto = httpClient.get {
        url { appendPathSegments("v1", "dolares", "binance") }
    }.body()

}