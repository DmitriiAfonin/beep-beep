package org.thechance.service_location.api.models

import io.ktor.server.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow

data class WebSocketTrip(val ownerSession: DefaultWebSocketServerSession){
    val locations = MutableStateFlow(LocationDto)
}