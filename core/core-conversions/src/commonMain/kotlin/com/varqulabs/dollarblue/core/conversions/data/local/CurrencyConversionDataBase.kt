package com.varqulabs.dollarblue.core.conversions.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.varqulabs.dollarblue.core.conversions.data.local.converters.InstantConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Database(
    entities = [CurrencyConversionEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(InstantConverter::class)
@ConstructedBy(CurrencyConversionDatabaseConstructor::class)
abstract class CurrencyConversionDataBase : RoomDatabase() {

    abstract fun currencyConversionDao(): CurrencyConversionDao
}

@Suppress("KotlinNoActualForExpect")
expect object CurrencyConversionDatabaseConstructor : RoomDatabaseConstructor<CurrencyConversionDataBase> {
    override fun initialize(): CurrencyConversionDataBase
}


fun getCurrencyConversionDatabase(
    builder: RoomDatabase.Builder<CurrencyConversionDataBase>,
): CurrencyConversionDataBase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}