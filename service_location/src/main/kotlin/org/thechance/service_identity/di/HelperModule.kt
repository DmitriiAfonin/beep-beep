package org.thechance.service_identity.di

import org.koin.dsl.module
import org.thechance.service_identity.utils.SocketHandler

val HelperModule = module {
    single<SocketHandler> { SocketHandler() }
}