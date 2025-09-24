package com.varqulabs.feature.calculator.data.repository

import com.varqulabs.feature.calculator.domain.model.BolivianUSDT
import com.varqulabs.feature.calculator.domain.repository.BolivianUSDTRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBolivianUSDTRepository : BolivianUSDTRepository {

    var offlineRate: BolivianUSDT? = null
    var onlineRate: BolivianUSDT? = null
    var throwApiError: Boolean = false

    override suspend fun getBolivianUSDT(): Flow<BolivianUSDT> = flow {
        offlineRate?.let { emit(it) }
        if (throwApiError) throw Exception("Error fetching Bolivian USDT")
        onlineRate?.let { emit(it) }
    }

}