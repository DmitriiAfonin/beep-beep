package org.thechance.api_gateway.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Taxi(
    val plateNumber: String,
    val color: Int,
    val type: String,
    val isAvailable: Boolean,
    val seats: Int,
    val driverId: String,
)