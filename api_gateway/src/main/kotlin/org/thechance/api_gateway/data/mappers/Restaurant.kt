package org.thechance.api_gateway.data.mappers

import org.thechance.api_gateway.data.model.restaurant.RestaurantResource
import org.thechance.api_gateway.endpoints.model.Restaurant

fun RestaurantResource.toRestaurant(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        description = description,
        priceLevel = priceLevel,
        rate = rate,
        phone = phone,
        openingTime = openingTime,
        closingTime = closingTime,
        address = address,
        location = location
    )
}