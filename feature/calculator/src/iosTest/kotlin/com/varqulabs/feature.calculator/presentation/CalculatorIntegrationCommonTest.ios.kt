package com.varqulabs.feature.calculator.presentation

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual open class CalculatorIntegrationTest actual constructor()

actual abstract class RoomDataBaseTest : CalculatorIntegrationTest() {
    actual fun getInMemoryDatabaseBuilder(): RoomDatabase.Builder<CurrencyConversionDataBase> {
        return Room.inMemoryDatabaseBuilder<CurrencyConversionDataBase>()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
    }
}