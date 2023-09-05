package org.thechance.service_identity.utils

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import java.util.concurrent.ConcurrentHashMap

class SocketHandler {

    val trip: ConcurrentHashMap<String, WebSocketTrip> = ConcurrentHashMap()

    suspend fun broadcastLocation(tripId: String) {
        val ownerSession = trip[tripId]?.ownerSession
        val locations = trip[tripId]?.locations

        try {
            locations?.drop(1)?.flowOn(Dispatchers.IO)?.collect { location ->
               if (location == trip[tripId]?.destinations?.value?.endPoint) {
                    // Close the user's session
                    ownerSession?.close(CloseReason(CloseReason.Codes.NORMAL, "Trip Completed"))
                    trip.remove(tripId)
                } else {
                    ownerSession?.sendSerialized(location)
                    println("Sending Location: $location")
                }
            }
        } catch (e: MultiErrorException) {
            ownerSession?.send(e.errorCodes.toString())
            ownerSession?.close()
        } finally {
            println("Finally: $trip")
            trip.remove(tripId)
        }
    }
}