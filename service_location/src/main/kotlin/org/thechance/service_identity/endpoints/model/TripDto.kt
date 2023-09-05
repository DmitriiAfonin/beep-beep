package org.thechance.service_identity.endpoints.model

import kotlinx.serialization.Serializable

@Serializable
data class TripDto(
    var startPoint: LocationDto? = null,
    var endPoint: LocationDto? = null,
)


