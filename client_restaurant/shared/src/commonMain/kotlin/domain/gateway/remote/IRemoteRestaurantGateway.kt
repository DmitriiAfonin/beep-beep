package domain.gateway.remote

import domain.entity.Restaurant


interface IRemoteRestaurantGateway {

    suspend fun updateRestaurantInfo(restaurant: Restaurant): Boolean
    suspend fun getRestaurantInfo(restaurantId: String): Restaurant

    suspend fun getRestaurantsByOwnerId(ownerId: String, token: String): List<Restaurant>
}