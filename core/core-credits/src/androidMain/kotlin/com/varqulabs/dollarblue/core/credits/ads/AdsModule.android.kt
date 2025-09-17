package com.varqulabs.dollarblue.core.credits.ads

import org.koin.core.module.Module
import org.koin.dsl.module

actual val adsModule: Module = module {
    single { RewardedAdService() }
}