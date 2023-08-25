package org.thechance.common.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.thechance.common.data.service.IFakeService
import org.thechance.common.data.service.FakeService


val DataSourceModule = module {
    singleOf(::FakeService) { bind<IFakeService>() }
}