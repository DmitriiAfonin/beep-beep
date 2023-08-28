package org.thechance.api_gateway.data.model.restaurant

import kotlinx.serialization.Serializable

@Serializable
data class RestaurantResource(
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
