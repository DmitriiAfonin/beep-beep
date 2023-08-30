package data.gateway.remote

import data.remote.mapper.toEntity
import data.remote.model.RestaurantDto
import domain.entity.Location
import domain.entity.Restaurant
import domain.gateway.remote.IRemoteRestaurantGateway
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders


class RemoteRestaurantGateWay(client: HttpClient) : IRemoteRestaurantGateway,
    BaseRemoteGateway(client = client) {

    override suspend fun getRestaurantsByOwnerId(ownerId: String, token: String): List<Restaurant> {
        return tryToExecute<List<RestaurantDto>> {
            get("restaurants") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                    append("Accept-Language", "ar")
                    append("Country-Code", "EG")
                }
            }
        }.map { it.toEntity() }
    }

    override suspend fun updateRestaurantInfo(restaurant: Restaurant): Boolean {
        return true
    }

    override suspend fun getRestaurantInfo(restaurantId: String): Restaurant {
        return getRestaurantsByOwnerId("7bf7ef77d907", "").find { it.id == restaurantId }
            ?: Restaurant(
                id = "",
                ownerId = "",
                address = "",
                location = Location(0.0, 0.0),
                phone = "",
                openingTime = "",
                closingTime = "",
                rate = 0.0,
                priceLevel = "",
                description = "",
                ownerUsername = "",
                name = ""
            )
    }

}