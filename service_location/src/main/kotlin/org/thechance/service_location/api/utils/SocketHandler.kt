package org.thechance.service_location.api.utils

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOn
import org.thechance.service_location.api.models.WebSocketTrip
import org.thechance.service_location.domain.util.exciptions.MultiErrorException
import java.util.concurrent.ConcurrentHashMap

class SocketHandler {

    val trip: ConcurrentHashMap<String, WebSocketTrip> = ConcurrentHashMap()

    suspend fun broadcastLocation(locationId: String) {

        val ownerSession = trip[locationId]?.ownerSession
        val locations = trip[locationId]?.locations

        try {
            locations
                ?.drop(1)
                ?.flowOn(Dispatchers.IO)
                ?.collect { location -> ownerSession?.sendSerialized(location) }
        } catch (e: MultiErrorException) {
            ownerSession?.send(e.errorCodes.toString())
            ownerSession?.close()
        } finally {
            this.trip.remove(locationId)
        }
    }
}