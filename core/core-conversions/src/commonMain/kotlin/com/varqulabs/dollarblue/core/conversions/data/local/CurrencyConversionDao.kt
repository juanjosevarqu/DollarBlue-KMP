package com.varqulabs.dollarblue.core.conversions.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyConversionDao {

    @Query("SELECT * FROM currency_conversions ORDER BY createdAt DESC")
    fun getAllSortedByMostRecent(): Flow<List<CurrencyConversionEntity>>

    @Query("SELECT * FROM currency_conversions ORDER BY createdAt ASC")
    fun getAllSortedByOldest(): Flow<List<CurrencyConversionEntity>>

    @Query("SELECT * FROM currency_conversions WHERE normalizedName LIKE :query ESCAPE '\' ORDER BY createdAt DESC")
    fun searchByName(query: String): Flow<List<CurrencyConversionEntity>>

    @Upsert
    suspend fun upsert(conversion: CurrencyConversionEntity)

    @Update
    suspend fun update(conversion: CurrencyConversionEntity)

    @Query("DELETE FROM currency_conversions WHERE localId = :id")
    suspend fun deleteByLocalId(id: Long)

}