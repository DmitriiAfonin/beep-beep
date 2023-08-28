package org.thechance.api_gateway.endpoints.model

import kotlinx.serialization.Serializable
import org.thechance.api_gateway.data.model.restaurant.LocationResource

@Serializable
data class Restaurant(
    val id: String,
    val name: String,
    val description: String?,
    val priceLevel: String?,
    val rate: Double?,
    val phone: String,
    val openingTime: String,
    val closingTime: String,
    val address: String,
    val location: LocationResource,
)
