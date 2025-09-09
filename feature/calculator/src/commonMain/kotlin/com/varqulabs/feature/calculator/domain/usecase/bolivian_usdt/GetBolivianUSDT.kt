package com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt

import com.varqulabs.core.service.utils.DataState
import com.varqulabs.feature.calculator.domain.model.BolivianUSDT
import com.varqulabs.feature.calculator.domain.repository.BolivianUSDTRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetBolivianUSDT(
    private val repository: BolivianUSDTRepository
) {
    operator fun invoke(): Flow<DataState<BolivianUSDT>> {
        return flow {
            emit(DataState.Loading)
            repository.getBolivianUSDT().collect { emit(DataState.Success(it)) }
        }.catch { e ->
            emit(DataState.Error(e.message ?: "Error de Red Desconocido"))
        }
    }
}