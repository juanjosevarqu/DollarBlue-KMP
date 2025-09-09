package com.varqulabs.feature.calculator.domain.service

import com.varqulabs.feature.calculator.data.remote.dto.BolivianUSDTDto

interface BolivianUSDTService {

    suspend fun getBolivianUSDTBinance(): BolivianUSDTDto

}