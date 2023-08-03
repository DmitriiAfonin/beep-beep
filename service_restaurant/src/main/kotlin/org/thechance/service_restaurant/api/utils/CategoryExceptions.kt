package org.thechance.service_restaurant.api.utils

import io.ktor.http.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.thechance.service_restaurant.utils.DeleteCategoryException
import org.thechance.service_restaurant.utils.MultiErrorException

fun StatusPagesConfig.categoryException() {

    exception<MultiErrorException> { call, exception ->
        call.respond(HttpStatusCode.BadRequest, exception.errorCodes)
    }

}