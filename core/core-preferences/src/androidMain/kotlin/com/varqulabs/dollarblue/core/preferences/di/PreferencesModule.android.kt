package com.varqulabs.dollarblue.core.preferences.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.varqulabs.dollarblue.core.preferences.createPreferencesDataStore
import org.koin.dsl.module

actual val dataStoreModule = module {
    single<DataStore<Preferences>> {
        createPreferencesDataStore()
    }
}