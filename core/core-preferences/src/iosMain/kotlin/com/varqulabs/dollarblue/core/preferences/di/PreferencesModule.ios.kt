package com.varqulabs.dollarblue.core.preferences.di

import com.varqulabs.dollarblue.core.preferences.createPreferencesDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module
    get() = module { single { createPreferencesDataStore() } }