package com.varqulabs.dollarblue.core.credits.di

import com.varqulabs.core.common.di.IO_DISPATCHER
import com.varqulabs.dollarblue.core.credits.data.CreditsRepositoryImpl
import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository
import com.varqulabs.dollarblue.core.credits.domain.usecase.AddCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.ConsumeCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.GetCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.ObserveCredits
import com.varqulabs.dollarblue.core.credits.presentation.CreditsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val creditsModule: Module = module {
    single<CreditsRepository> { CreditsRepositoryImpl(get()) }
    single<ObserveCredits> { ObserveCredits(get()) }
    single<GetCredits> { GetCredits(get()) }
    single<AddCredits> { AddCredits(get()) }
    single<ConsumeCredits> { ConsumeCredits(get()) }
    viewModel {
        CreditsViewModel(
            addCredits = get<AddCredits>(),
            dispatcher = get(named(IO_DISPATCHER)),
        )
    }
}