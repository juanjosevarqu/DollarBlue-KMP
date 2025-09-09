package com.varqulabs.dollarblue.core.preferences.di

import com.varqulabs.dollarblue.core.preferences.data.repository.PreferencesRepositoryImpl
import com.varqulabs.dollarblue.core.preferences.domain.PreferencesRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataStoreModule: Module

val preferencesModule: Module = module {
    includes(dataStoreModule)
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
}