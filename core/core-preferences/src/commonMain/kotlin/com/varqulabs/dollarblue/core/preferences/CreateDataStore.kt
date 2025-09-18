package com.varqulabs.dollarblue.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import okio.Path.Companion.toPath

const val DOLLAR_BLUE_FILE_NAME = "dollarblue"
internal const val PREFERENCES_DATA_STORE_FILE_NAME = "$DOLLAR_BLUE_FILE_NAME.preferences_pb"

fun producePreferencesDataStore(path: String) : DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(
        scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    ) {
        path.toPath()
    }
}

expect fun createPreferencesDataStore(): DataStore<Preferences>
