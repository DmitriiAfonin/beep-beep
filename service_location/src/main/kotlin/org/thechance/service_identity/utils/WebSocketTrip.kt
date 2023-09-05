package org.thechance.service_identity.utils

import io.ktor.server.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.thechance.service_identity.endpoints.model.LocationDto
import org.thechance.service_identity.endpoints.model.TripDto

data class WebSocketTrip(
    val ownerSession: DefaultWebSocketServerSession,
){
    val locations = MutableStateFlow(LocationDto())
    val destinations = MutableStateFlow(TripDto())
}