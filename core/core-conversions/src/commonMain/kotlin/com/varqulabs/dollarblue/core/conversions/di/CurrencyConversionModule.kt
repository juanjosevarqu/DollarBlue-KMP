package com.varqulabs.dollarblue.core.conversions.di

import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionDao
import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionDataBase
import com.varqulabs.dollarblue.core.conversions.data.repository.CurrencyConversionRepositoryImpl
import com.varqulabs.dollarblue.core.conversions.domain.CurrencyConversionRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect val currencyConversionDataBaseModule: Module

val currencyConversionModule: Module = module {
    includes(currencyConversionDataBaseModule)
    single<CurrencyConversionDao> { get<CurrencyConversionDataBase>().currencyConversionDao() }
    single<CurrencyConversionRepository> { CurrencyConversionRepositoryImpl(get()) }
}