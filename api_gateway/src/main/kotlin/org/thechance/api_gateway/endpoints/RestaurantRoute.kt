package org.thechance.api_gateway.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.thechance.api_gateway.endpoints.gateway.IRestaurantGateway
import org.thechance.api_gateway.endpoints.utils.extractLocalizationHeader
import org.thechance.api_gateway.endpoints.utils.respondWithResult
import java.util.*

fun Route.restaurantRoute() {
    val restaurantGateway: IRestaurantGateway by inject()

    route("/restaurants") {

        authenticate("auth-jwt") {
            get {
                val tokenClaim = call.principal<JWTPrincipal>()
                val ownerId = tokenClaim?.payload?.subject.toString()
                val permissions = tokenClaim?.payload?.getClaim("permissions")?.asList(Int::class.java)
                    ?: emptyList()
                val (language, countryCode) = extractLocalizationHeader()

                val result = restaurantGateway.getRestaurantsByOwnerId(ownerId, Locale(language, countryCode))


                respondWithResult(HttpStatusCode.OK, result)
            }
        }
    }
}
