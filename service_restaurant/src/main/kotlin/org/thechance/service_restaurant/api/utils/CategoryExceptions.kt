package org.thechance.service_restaurant.api.utils

import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.statuspages.StatusPagesConfig
import io.ktor.server.response.respond
import org.thechance.service_restaurant.utils.MultiErrorException

fun StatusPagesConfig.categoryException() {

    exception<MultiErrorException> { call, exception ->
        call.respond(HttpStatusCode.BadRequest, exception.errorCodes)
    }

    exception<Throwable> { call, throwable ->
        call.respond(HttpStatusCode.InternalServerError, throwable.message ?: "Unknown Error")
    }
}