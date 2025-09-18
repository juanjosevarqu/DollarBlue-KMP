package com.varqulabs.dollarblue.core.ads.di

import com.varqulabs.dollarblue.core.ads.data.RewardedAdService
import org.koin.core.module.Module
import org.koin.dsl.module

actual val adsModule: Module = module {
    single { RewardedAdService() }
}