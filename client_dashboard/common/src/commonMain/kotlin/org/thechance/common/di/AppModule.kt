package org.thechance.common.di

import org.thechance.common.ui.screen.first.FirstScreenModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun appModule() = module {
    factory { FirstScreenModel() }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        appModule(),
    )
}