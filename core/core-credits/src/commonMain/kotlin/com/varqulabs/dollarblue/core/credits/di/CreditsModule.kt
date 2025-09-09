package com.varqulabs.dollarblue.core.credits.di

import com.varqulabs.dollarblue.core.credits.data.CreditsRepositoryImpl
import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository
import com.varqulabs.dollarblue.core.credits.domain.usecase.AddCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.ConsumeCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.GetCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.ObserveCredits
import org.koin.core.module.Module
import org.koin.dsl.module

val creditsModule: Module = module {
    single<CreditsRepository> { CreditsRepositoryImpl(get()) }
    single<ObserveCredits> { ObserveCredits(get()) }
    single<GetCredits> { GetCredits(get()) }
    single<AddCredits> { AddCredits(get()) }
    single<ConsumeCredits> { ConsumeCredits(get()) }
}