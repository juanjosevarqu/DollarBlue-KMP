package com.varqulabs.dollarblue.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

private lateinit var applicationContext: Context

fun initPreferencesDataStore(context: Context) {
    applicationContext = context
}

fun getPreferencesDataStorePath(appContext: Context): String =
    appContext.filesDir.resolve(PREFERENCES_DATA_STORE_FILE_NAME).absolutePath

actual fun createPreferencesDataStore(): DataStore<Preferences> {
    val path = getPreferencesDataStorePath(applicationContext)
    return producePreferencesDataStore(path)
}