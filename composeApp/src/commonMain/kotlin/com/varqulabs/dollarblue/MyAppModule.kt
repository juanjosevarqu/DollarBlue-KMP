package com.varqulabs.dollarblue

import com.varqulabs.core.common.di.dispatchersModule
import com.varqulabs.dollarblue.core.conversions.di.currencyConversionModule
import com.varqulabs.dollarblue.core.credits.di.creditsModule
import com.varqulabs.dollarblue.core.preferences.di.preferencesModule
import com.varqulabs.dollarblue.welcome.di.welcomeModule
import com.varqulabs.feature.calculator.di.calculatorModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration? = null) {
    startKoin {
        appDeclaration?.invoke(this)
        modules(
            dispatchersModule,
            preferencesModule,
            currencyConversionModule,
            creditsModule,
            welcomeModule,
            calculatorModule,
        )
    }
}