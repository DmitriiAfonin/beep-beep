package org.thechance.api_gateway.endpoints.model

import kotlinx.serialization.Serializable

@Serializable
data class OwnerRestaurants(
    val name: String? = null,
    val description: String? = null,
    val priceLevel: String? = null,
    val currency: String? = null,
    val phone: String? = null,
    val openingTime: String? = null,
    val closingTime: String? = null,
)
