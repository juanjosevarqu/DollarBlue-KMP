package com.varqulabs.dollarblue.core.conversions.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

private lateinit var applicationContext: Context

fun initCurrencyConversionDatabase(context: Context) {
    applicationContext = context
}

fun getCurrencyConversionDatabaseBuilder(): RoomDatabase.Builder<CurrencyConversionDataBase> {
    val dbFile = applicationContext.getDatabasePath("currency_conversion_database.db")
    return Room.databaseBuilder<CurrencyConversionDataBase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
}