package com.varqulabs.feature.calculator.di

import com.varqulabs.core.service.di.serviceModule
import com.varqulabs.dollarblue.core.preferences.domain.PreferencesRepository
import com.varqulabs.feature.calculator.data.remote.BolivianUSDTServiceImpl
import com.varqulabs.feature.calculator.data.repository.BolivianUSDTRepositoryImpl
import com.varqulabs.feature.calculator.domain.repository.BolivianUSDTRepository
import com.varqulabs.feature.calculator.domain.service.BolivianUSDTService
import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val bolivianUSDTModule: Module = module {
    includes(serviceModule(baseUrl = "https://bo.dolarapi.com"))
    single<BolivianUSDTService> { BolivianUSDTServiceImpl(get<HttpClient>()) }
    single<BolivianUSDTRepository> { BolivianUSDTRepositoryImpl(get<BolivianUSDTService>(), get<PreferencesRepository>()) }
    single<GetBolivianUSDT> { GetBolivianUSDT(get<BolivianUSDTRepository>()) }
}