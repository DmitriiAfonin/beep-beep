package org.thechance.service_identity.utils

import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.statuspages.StatusPagesConfig
import io.ktor.server.response.respond

fun StatusPagesConfig.configureStatusPages() {
    exception<MultiErrorException>{ call, exception ->
        call.respond(HttpStatusCode.NotFound, exception.errorCodes)
    }

    exception<Throwable> { call, throwable ->
        call.respond(HttpStatusCode.BadRequest, throwable.message.toString())
    }
}