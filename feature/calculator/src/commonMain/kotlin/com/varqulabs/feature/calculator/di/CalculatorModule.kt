package com.varqulabs.feature.calculator.di

import com.varqulabs.dollarblue.core.preferences.di.preferencesModule
import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import com.varqulabs.feature.calculator.presentation.CalculatorViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformDispatcher: CoroutineDispatcher

val calculatorModule: Module = module {
    includes(preferencesModule)
    includes(bolivianUSDTModule)
    viewModel { CalculatorViewModel(get<GetBolivianUSDT>()) }
}