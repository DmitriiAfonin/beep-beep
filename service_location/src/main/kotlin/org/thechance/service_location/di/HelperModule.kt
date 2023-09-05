package org.thechance.service_location.di

import org.koin.dsl.module
import org.thechance.service_location.api.utils.SocketHandler

val HelperModule = module {
    single<SocketHandler> { SocketHandler() }
}