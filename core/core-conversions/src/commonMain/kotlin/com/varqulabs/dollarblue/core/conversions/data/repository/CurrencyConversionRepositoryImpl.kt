package com.varqulabs.dollarblue.core.conversions.data.repository

import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionDao
import com.varqulabs.dollarblue.core.conversions.data.local.mapToDomain
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import com.varqulabs.dollarblue.core.conversions.domain.model.mapToEntity
import com.varqulabs.dollarblue.core.conversions.domain.CurrencyConversionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class CurrencyConversionRepositoryImpl(
    private val dao: CurrencyConversionDao
): CurrencyConversionRepository {

    override fun getAllSortedByMostRecent(): Flow<List<CurrencyConversion>> {
        return dao.getAllSortedByMostRecent().map { list -> list.map { it.mapToDomain() } }
    }

    override fun getAllSortedByOldest(): Flow<List<CurrencyConversion>> {
        return dao.getAllSortedByOldest().map { list -> list.map { it.mapToDomain() } }
    }

    override fun searchByName(query: String): Flow<List<CurrencyConversion>> {
        return dao.searchByName(query).map { list -> list.map { it.mapToDomain() } }
            .distinctUntilChanged()
    }

    override suspend fun insert(conversion: CurrencyConversion) {
        dao.upsert(conversion.mapToEntity())
    }

    override suspend fun update(conversion: CurrencyConversion) {
        dao.update(conversion.mapToEntity())
    }

    override suspend fun deleteByLocalId(id: Long) {
        dao.deleteByLocalId(id)
    }
}