package com.varqulabs.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MAIN_DISPATCHER = "MainDispatcher"
const val IO_DISPATCHER = "IODispatcher"

val dispatchersModule = module {
    single<CoroutineDispatcher>(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    single<CoroutineDispatcher>(named(IO_DISPATCHER)) { Dispatchers.IO }
}
