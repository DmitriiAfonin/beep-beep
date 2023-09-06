package org.thechance.service_location.api.endpoint

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.koin.ktor.ext.inject
import org.thechance.service_identity.endpoints.model.LocationDto
import org.thechance.service_location.api.models.WebSocketTrip
import org.thechance.service_location.api.utils.SocketHandler

fun Route.locationRoutes() {

    val socketHandler: SocketHandler by inject()

    route("/location") {

        post("/{tripId}") {
            val tripId = call.parameters["tripId"]?.trim().orEmpty()
            val location = call.receive<LocationDto>()
            println("location: ${socketHandler.trip[tripId]}")
            socketHandler.trip[tripId]?.locations?.emit(location)
            call.respond(HttpStatusCode.Created, location)
        }

        webSocket("/{tripId}") {
            val tripId = call.parameters["tripId"]?.trim().orEmpty()
            socketHandler.trip[tripId] = WebSocketTrip(this)
            socketHandler.broadcastLocation(tripId)

        }

    }

}

