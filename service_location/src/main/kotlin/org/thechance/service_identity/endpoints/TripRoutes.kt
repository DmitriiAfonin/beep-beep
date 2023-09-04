package org.thechance.service_identity.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.koin.ktor.ext.inject
import org.thechance.service_identity.endpoints.model.LocationDto
import org.thechance.service_identity.utils.SocketHandler
import org.thechance.service_identity.utils.WebSocketTrip
import kotlin.collections.set

fun Route.tripRoutes() {

    val socketHandler: SocketHandler by inject()


    route("/trip") {
        post("/start/{tripId}") {
            val tripId = call.parameters["tripId"]?.trim().orEmpty()
            val location = call.receive<LocationDto>()
            println("location: ${socketHandler.trip[tripId]}")
            socketHandler.trip[tripId]?.locations?.emit(location) // emit location to all clients
            call.respond(HttpStatusCode.Created, location)
        }

        webSocket("/{tripId}") {
            val tripId = call.parameters["tripId"]?.trim().orEmpty()
            socketHandler.trip[tripId] = WebSocketTrip(this)
            socketHandler.broadcastLocation(tripId)
        }

    }
}