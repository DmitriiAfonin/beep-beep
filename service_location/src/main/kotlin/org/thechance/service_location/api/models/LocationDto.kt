package org.thechance.service_location.api.models

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val latitude: Double? = null,
    val longitude: Double? = null,
)