package org.thechance.api_gateway.endpoints.gateway

import org.thechance.api_gateway.data.model.restaurant.RestaurantResource

interface IRestaurantGateway {
    suspend fun getRestaurant(restaurantId: String): RestaurantResource
}