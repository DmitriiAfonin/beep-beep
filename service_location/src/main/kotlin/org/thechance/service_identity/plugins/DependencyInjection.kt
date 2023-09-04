package org.thechance.service_identity.plugins

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.thechance.service_identity.di.BeepClient

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(BeepClient)
    }
}