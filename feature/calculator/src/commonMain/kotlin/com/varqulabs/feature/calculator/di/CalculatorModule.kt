package com.varqulabs.feature.calculator.di

import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import com.varqulabs.feature.calculator.presentation.CalculatorViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val calculatorModule: Module = module {
    includes(bolivianUSDTModule)
    viewModel { CalculatorViewModel(get<GetBolivianUSDT>()) }
}