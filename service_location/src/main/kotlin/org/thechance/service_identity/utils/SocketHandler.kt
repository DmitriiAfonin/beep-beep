package org.thechance.service_identity.utils

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.ConcurrentHashMap

class SocketHandler {

    val trip: ConcurrentHashMap<String, WebSocketTrip> = ConcurrentHashMap()

    suspend fun broadcastLocation(tripId: String) {

        val ownerSession = trip[tripId]?.ownerSession//session
        val locations = trip[tripId]?.locations//Latitude and Longitude
        println("ownerSession: $ownerSession")
        println("broadcastLocation locations: $locations")
        try {
            locations
                ?.drop(1)
                ?.flowOn(Dispatchers.IO)
                ?.collect { location ->
                         ownerSession?.sendSerialized(location)
                    println("collect: $location")
                }
            println("no error: $locations")
        } catch (e: MultiErrorException) {
            ownerSession?.send(e.errorCodes.toString())
            ownerSession?.close()
            println("catch: ${e.errorCodes}")
        } finally {
            println("finally: $trip")
              trip.remove(tripId)
        }
    }
}