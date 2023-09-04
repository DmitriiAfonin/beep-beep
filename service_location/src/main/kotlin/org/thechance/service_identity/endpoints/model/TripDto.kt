package org.thechance.service_identity.endpoints.model

import kotlinx.serialization.Serializable

@Serializable
data class TripDto(
    val tripId: String,
    val location: LocationDto
)


