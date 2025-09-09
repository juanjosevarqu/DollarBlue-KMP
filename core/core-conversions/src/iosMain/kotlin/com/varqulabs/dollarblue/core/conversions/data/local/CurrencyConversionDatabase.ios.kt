package com.varqulabs.dollarblue.core.conversions.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getCurrencyConversionDatabaseBuilder(): RoomDatabase.Builder<CurrencyConversionDataBase> {
    val dbFilePath = documentDirectory() + "/currency_conversion_database.db"
    return Room.databaseBuilder<CurrencyConversionDataBase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )

    return requireNotNull(documentDirectory?.path)
}