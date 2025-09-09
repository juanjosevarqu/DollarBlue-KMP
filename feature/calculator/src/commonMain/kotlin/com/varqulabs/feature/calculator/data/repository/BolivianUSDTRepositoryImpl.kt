package com.varqulabs.feature.calculator.data.repository

import com.varqulabs.dollarblue.core.preferences.domain.PreferencesRepository
import com.varqulabs.feature.calculator.data.local.CalculatorPreferenceKey.BOB_VALUE
import com.varqulabs.feature.calculator.data.local.CalculatorPreferenceKey.LAST_UPDATE
import com.varqulabs.feature.calculator.data.remote.dto.mapToDomain
import com.varqulabs.feature.calculator.domain.model.BolivianUSDT
import com.varqulabs.feature.calculator.domain.repository.BolivianUSDTRepository
import com.varqulabs.feature.calculator.domain.service.BolivianUSDTService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BolivianUSDTRepositoryImpl(
    private val service: BolivianUSDTService,
    private val preferences: PreferencesRepository,
): BolivianUSDTRepository {

    override suspend fun getBolivianUSDT(): Flow<BolivianUSDT> = flow {
        val bobLastValue = preferences.getNormalPreference(key = BOB_VALUE, defaultValue = 14.0)
        val bobLastUpdate = preferences.getNormalPreference(key = LAST_UPDATE, defaultValue = "")
        if (bobLastValue != 0.0 || bobLastUpdate.isNotEmpty()) {
            emit(BolivianUSDT(valueSell = bobLastValue, dateUpdated = bobLastUpdate))
        }
        val apiResult = service.getBolivianUSDTBinance().mapToDomain()
        preferences.putPreference(key = BOB_VALUE, value = apiResult.valueSell)
        preferences.putPreference(key = LAST_UPDATE, value = apiResult.dateUpdated)
        if (apiResult.dateUpdated != bobLastUpdate || apiResult.valueSell != bobLastValue) {
            emit(apiResult)
        }
    }

}

