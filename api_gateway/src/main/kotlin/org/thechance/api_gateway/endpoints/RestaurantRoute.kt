package org.thechance.api_gateway.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.thechance.api_gateway.endpoints.gateway.IRestaurantGateway
import org.thechance.api_gateway.endpoints.utils.respondWithResult

fun Route.restaurantRoutes() {
    val gateway: IRestaurantGateway by inject()

    route("restaurant") {
        authenticate("auth-jwt") {
            get("/{id}") {
                val tokenClaim = call.principal<JWTPrincipal>()
                val permissions = tokenClaim?.payload?.getClaim("permissions")?.asList(Int::class.java)
                    ?: emptyList()

                val restaurantId = call.parameters["id"]?.trim().toString()

                if (permissions.contains(2) || permissions.contains(3)) {
                    val restaurant = gateway.getRestaurant(restaurantId)
                    respondWithResult(statusCode = HttpStatusCode.OK, result = restaurant)
                }
            }
        }
    }

}