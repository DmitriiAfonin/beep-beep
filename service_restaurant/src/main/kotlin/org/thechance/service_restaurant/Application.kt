package org.thechance.service_restaurant

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.thechance.service_restaurant.plugins.configureDependencyInjection
import org.thechance.service_restaurant.plugins.configureMonitoring
import org.thechance.service_restaurant.plugins.configureRouting
import org.thechance.service_restaurant.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDependencyInjection()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
