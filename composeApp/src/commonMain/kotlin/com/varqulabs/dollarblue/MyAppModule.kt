package com.varqulabs.dollarblue

import com.varqulabs.dollarblue.core.conversions.di.currencyConversionDataBaseModule
import com.varqulabs.dollarblue.core.preferences.di.preferencesModule
import com.varqulabs.feature.calculator.di.calculatorModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration? = null) {
    startKoin {
        appDeclaration?.invoke(this)
        modules(
            preferencesModule,
            currencyConversionDataBaseModule,
            calculatorModule,
        )
    }
}