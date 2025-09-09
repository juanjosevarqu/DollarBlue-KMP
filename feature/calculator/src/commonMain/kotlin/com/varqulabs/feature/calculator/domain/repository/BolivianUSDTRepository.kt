package com.varqulabs.feature.calculator.domain.repository

import com.varqulabs.feature.calculator.domain.model.BolivianUSDT
import kotlinx.coroutines.flow.Flow

interface BolivianUSDTRepository {

    suspend fun getBolivianUSDT(): Flow<BolivianUSDT>

}