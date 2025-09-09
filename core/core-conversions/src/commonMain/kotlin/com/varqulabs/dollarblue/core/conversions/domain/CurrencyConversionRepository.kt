package com.varqulabs.dollarblue.core.conversions.domain

import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionRepository {

    fun getAllSortedByMostRecent(): Flow<List<CurrencyConversion>>

    fun getAllSortedByOldest(): Flow<List<CurrencyConversion>>

    fun searchByName(query: String): Flow<List<CurrencyConversion>>

    suspend fun insert(conversion: CurrencyConversion)

    suspend fun update(conversion: CurrencyConversion)

    suspend fun deleteByLocalId(localId: Long)
}