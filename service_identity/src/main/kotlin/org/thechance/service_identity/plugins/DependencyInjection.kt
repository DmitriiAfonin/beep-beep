package org.thechance.service_identity.plugins

import io.ktor.server.application.*
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin
import org.thechance.service_identity.di.IdentityModule

fun Application.configureDependencyInjection() {
    install(Koin) { modules(IdentityModule().module) }
}