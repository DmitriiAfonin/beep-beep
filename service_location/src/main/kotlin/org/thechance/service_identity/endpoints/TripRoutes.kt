package org.thechance.service_identity.endpoints


import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import org.thechance.service_identity.endpoints.model.LocationDto
import org.thechance.service_identity.endpoints.model.TripDto
import org.thechance.service_identity.utils.SocketHandler
import org.thechance.service_identity.utils.WebSocketTrip
import kotlin.collections.set

fun Route.tripRoutes() {

    val socketHandler: SocketHandler by inject()


    route("/trip") {
        webSocket("/driver-location/{tripId}") {
            val tripId = call.parameters["tripId"]?.trim().orEmpty()
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val location = Json.decodeFromString<LocationDto>(frame.readText())
                    socketHandler.trip[tripId]?.locations?.emit(location)
                    println("Driver Location: $location")

                    // Check if driver's location matches the endpoint
                    if (location == socketHandler.trip[tripId]?.destinations?.value?.endPoint) {
                        // Close the driver's session
                        close(CloseReason(CloseReason.Codes.NORMAL, "Trip Completed"))
                        socketHandler.trip.remove(tripId)
                        break
                    }
                }
            }
        }

        webSocket("/start/{tripId}") {
            val tripId = call.parameters["tripId"]?.trim().orEmpty()
            socketHandler.trip[tripId] = WebSocketTrip(this)
            for (frame in incoming) {
                if (frame is Frame.Text) {

                    val trip = Json.decodeFromString<TripDto>(frame.readText())
                    // Set the user's start and endpoint
                    if (socketHandler.trip[tripId]?.destinations?.value?.endPoint == null) {
                        socketHandler.trip[tripId]?.destinations?.emit(trip)
                    }
                    socketHandler.broadcastLocation(tripId)

                    // Check if driver's location matches the endpoint
                    println("User Trip: $trip")
                }
            }
        }
    }
}