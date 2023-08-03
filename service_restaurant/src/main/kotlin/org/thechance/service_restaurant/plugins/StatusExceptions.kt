package org.thechance.service_restaurant.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.respond
import org.thechance.service_restaurant.api.utils.categoryException

fun Application.configureStatusExceptions() {
    install(StatusPages) {
        categoryException()
        exception<Throwable> { call, _ ->
            call.respond(HttpStatusCode.InternalServerError,"2000")
        }
    }
}


