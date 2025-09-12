package com.varqulabs.dollarblue.welcome.di

import com.varqulabs.core.common.di.IO_DISPATCHER
import com.varqulabs.dollarblue.welcome.domain.usecase.AcceptTerms
import com.varqulabs.dollarblue.welcome.domain.usecase.ObserveTermsAccepted
import com.varqulabs.dollarblue.welcome.presentation.WelcomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val welcomeModule: Module = module {
    single<ObserveTermsAccepted> { ObserveTermsAccepted(get()) }
    single<AcceptTerms> { AcceptTerms(get()) }
    viewModel { WelcomeViewModel(
        observeTermsAccepted = get<ObserveTermsAccepted>(),
        acceptTerms = get<AcceptTerms>(),
        dispatcher = get(named(IO_DISPATCHER)),
    ) }
}