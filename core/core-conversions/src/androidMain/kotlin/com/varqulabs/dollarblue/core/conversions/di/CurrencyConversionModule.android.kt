package com.varqulabs.dollarblue.core.conversions.di

import com.varqulabs.dollarblue.core.conversions.data.local.getCurrencyConversionDatabase
import com.varqulabs.dollarblue.core.conversions.data.local.getCurrencyConversionDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val currencyConversionDataBaseModule: Module
    get() = module {
        single {
            val builder = getCurrencyConversionDatabaseBuilder()
            getCurrencyConversionDatabase(builder)
        }
    }