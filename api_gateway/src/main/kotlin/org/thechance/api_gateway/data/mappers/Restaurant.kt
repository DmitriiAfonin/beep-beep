package org.thechance.api_gateway.data.mappers

import org.thechance.api_gateway.data.model.restaurant.RestaurantResource
import org.thechance.api_gateway.endpoints.model.OwnerRestaurants

fun RestaurantResource.toOwnerRestaurants(): OwnerRestaurants {
    return OwnerRestaurants(
        name = name,
        description = description,
        priceLevel = priceLevel,
        currency = currency,
        phone = phone,
        openingTime = openingTime,
        closingTime = closingTime,
    )
}