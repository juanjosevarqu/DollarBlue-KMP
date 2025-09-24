package com.varqulabs.feature.calculator.presentation

import com.varqulabs.dollarblue.core.conversions.domain.CurrencyConversionRepository
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import kotlinx.coroutines.flow.Flow

class FakeCurrencyConversionRepository : CurrencyConversionRepository {
    override fun getAllSortedByMostRecent(): Flow<List<CurrencyConversion>> {
        TODO("Not yet implemented")
    }

    override fun getAllSortedByOldest(): Flow<List<CurrencyConversion>> {
        TODO("Not yet implemented")
    }

    override fun searchByName(query: String): Flow<List<CurrencyConversion>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(conversion: CurrencyConversion) {
        TODO("Not yet implemented")
    }

    override suspend fun update(conversion: CurrencyConversion) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteByLocalId(localId: Long) {
        TODO("Not yet implemented")
    }

}