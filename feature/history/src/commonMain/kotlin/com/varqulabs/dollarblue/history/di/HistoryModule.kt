package com.varqulabs.dollarblue.history.di

import com.varqulabs.core.common.di.IO_DISPATCHER
import com.varqulabs.dollarblue.core.credits.domain.usecase.ObserveCredits
import com.varqulabs.dollarblue.history.domain.usecase.DeleteCurrencyConversion
import com.varqulabs.dollarblue.history.domain.usecase.GetCurrencyConversionsByMostRecent
import com.varqulabs.dollarblue.history.presentation.HistoryViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val historyModule: Module = module {
    single<GetCurrencyConversionsByMostRecent> { GetCurrencyConversionsByMostRecent(get()) }
    single<DeleteCurrencyConversion> { DeleteCurrencyConversion(get()) }
    viewModel {
        HistoryViewModel(
            observeCredits = get<ObserveCredits>(),
            getCurrencyConversionsByMostRecent = get<GetCurrencyConversionsByMostRecent>(),
            deleteCurrencyConversion = get<DeleteCurrencyConversion>(),
            dispatcher = get(named(IO_DISPATCHER)),
        )
    }
}