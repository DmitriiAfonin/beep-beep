package org.thechance.common.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.thechance.common.data.local.datasource.ILocalDataSource
import org.thechance.common.data.local.datasource.PDFExportDataSource


val DataSourceModule = module {
    singleOf(::PDFExportDataSource) { bind<ILocalDataSource>() }
}