package com.varqulabs.feature.calculator.di

import com.varqulabs.core.common.di.IO_DISPATCHER
import com.varqulabs.dollarblue.core.credits.domain.usecase.GetCredits
import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import com.varqulabs.feature.calculator.domain.usecase.currency_conversion.SaveConversion
import com.varqulabs.feature.calculator.domain.usecase.currency_conversion.SaveConversionAndConsumeCredits
import com.varqulabs.feature.calculator.presentation.CalculatorViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val calculatorModule: Module = module {
    includes(bolivianUSDTModule)
    single<SaveConversion> { SaveConversion(get()) }
    single<SaveConversionAndConsumeCredits> { SaveConversionAndConsumeCredits(get(), get()) }
    viewModel { CalculatorViewModel(
        getBolivianUSDT = get<GetBolivianUSDT>(),
        getCredits = get<GetCredits>(),
        saveConversion = get<SaveConversion>(),
        dispatcher = get(named(IO_DISPATCHER)),
    ) }
}