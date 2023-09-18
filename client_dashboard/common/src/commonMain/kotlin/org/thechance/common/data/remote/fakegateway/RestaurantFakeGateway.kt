package org.thechance.common.data.remote.fakegateway

import org.thechance.common.data.remote.mapper.toEntity
import org.thechance.common.data.remote.model.DataWrapperDto
import org.thechance.common.data.remote.model.RestaurantDto
import org.thechance.common.data.remote.model.toEntity
import org.thechance.common.domain.entity.Cuisine
import org.thechance.common.domain.entity.DataWrapper
import org.thechance.common.domain.entity.NewRestaurantInfo
import org.thechance.common.domain.entity.Restaurant
import org.thechance.common.domain.getway.IRestaurantGateway
import java.util.UUID
import kotlin.math.ceil

class RestaurantFakeGateway : IRestaurantGateway {

    override suspend fun getRestaurants(
        pageNumber: Int,
        numberOfRestaurantsInPage: Int,
        restaurantName: String,
        rating: Double?,
        priceLevel: String?,
    ): DataWrapper<Restaurant> {
        var restaurants = restaurants.toEntity()
        if (restaurantName.isNotEmpty()) {
            restaurants = restaurants.filter {
                it.name.startsWith(
                    restaurantName,
                    true
                )
            }
        }
        if (rating != null && priceLevel != null) {
            restaurants = restaurants.filter {
                it.priceLevel == priceLevel
            }
        }
        val startIndex = (pageNumber - 1) * numberOfRestaurantsInPage
        val endIndex = startIndex + numberOfRestaurantsInPage
        val numberOfPages = ceil(restaurants.size / (numberOfRestaurantsInPage * 1.0)).toInt()
        return try {
            DataWrapperDto(
                totalPages = numberOfPages,
                result = restaurants.subList(startIndex, endIndex.coerceAtMost(restaurants.size)),
                totalResult = restaurants.size
            ).toEntity()
        } catch (e: Exception) {
            DataWrapperDto(
                totalPages = numberOfPages,
                result = restaurants,
                totalResult = restaurants.size
            ).toEntity()
        }
    }

    override suspend fun createRestaurant(restaurant: NewRestaurantInfo): Restaurant {
        return Restaurant(
            id = "7",
            name = restaurant.name,
            ownerId = restaurant.ownerUsername,
            phone = restaurant.phoneNumber,
            openingTime = restaurant.openingTime,
            closingTime = restaurant.closingTime,
            rate = 0.0,
            priceLevel = "",
        )
    }

    override suspend fun deleteRestaurant(id: String): Boolean {
        restaurants.remove(restaurants.find { it.id == id })
        return true
    }

    override suspend fun getCuisines(): List<Cuisine> {
        return cuisines
    }


    override suspend fun createCuisine(cuisineName: String): Cuisine {
        val newCuisine = Cuisine(UUID.randomUUID().toString(), cuisineName)
        cuisines.add(newCuisine)
        return newCuisine
    }

    override suspend fun deleteCuisine(cuisineId: String) {
        cuisines.find { it.id == cuisineId }?.let {
            cuisines.remove(it)
        }
    }

    private val cuisines = mutableListOf<Cuisine>(
            Cuisine("1", "Angolan cuisine"),
            Cuisine("", "Cameroonian cuisine"),
            Cuisine("", "Chadian cuisine"),
            Cuisine("", "Congolese cuisine"),
            Cuisine("", "Centrafrican cuisine"),
            Cuisine("", "Equatorial Guinea cuisine"),
            Cuisine("", "Gabonese cuisine"),
            Cuisine("", "Santomean cuisine"),
            Cuisine("", "Burundian cuisine"),
            Cuisine("", "Djiboutian cuisine"),
            Cuisine("", "Eritrean cuisine"),
            Cuisine("", "Ethiopian cuisine"),
            Cuisine("", "Kenyan cuisine"),
            Cuisine("", "Maasai cuisine"),
            Cuisine("", "Rwandan cuisine"),
            Cuisine("", "Somali cuisine"),
            Cuisine("", "South Sudanese cuisine"),
            Cuisine("", "Tanzanian cuisine"),
            Cuisine("", "Zanzibari cuisine"),
            Cuisine("", "Ugandan cuisine"),
    )


    private val restaurants = mutableListOf<RestaurantDto>(
            RestaurantDto(
                    id = "8c90c4c6-1e69-47f3-aa59-2edcd6f0057b",
                    name = "Mujtaba Restaurant",
                    username = "mujtaba",
                    phone = "0532465722",
                    rating = 0.4,
                    priceLevel = 1,
                    openingTime = "06:30",
                    closingTime = "22:30",
            ),
            RestaurantDto(
                    id = "6e21s4f-aw32-fs3e-fe43-aw56g4yr324",
                    name = "Karrar Restaurant",
                    username = "karrar",
                    phone = "0535232154",
                    rating = 3.5,
                    priceLevel = 1,
                    openingTime = "12:00",
                    closingTime = "23:00",
            ),
            RestaurantDto(
                    id = "7a33sax-aw32-fs3e-12df-42ad6x352zse",
                    name = "Saif Restaurant",
                    username = "saif",
                    phone = "0554627893",
                    rating = 4.0,
                    priceLevel = 3,
                    openingTime = "12:00",
                    closingTime = "23:00",
            ),
            RestaurantDto(
                    id = "7y1z47c-s2df-76de-dwe2-42ad6x352zse",
                    name = "Nada Restaurant",
                    username = "nada",
                    phone = "0524242766",
                    rating = 3.4,
                    priceLevel = 2,
                    openingTime = "12:00",
                    closingTime = "23:00",
            ),
            RestaurantDto(
                    id = "3e1f5d4a-8317-4f13-aa89-2c094652e6a3",
                    name = "Asia Restaurant",
                    username = "asia",
                    phone = "0528242165",
                    rating = 2.9,
                    priceLevel = 1,
                    openingTime = "12:00",
                    closingTime = "23:00",
            ),
            RestaurantDto(
                    id = "7a1bfe39-4b2c-4f76-bde0-82da2eaf9e99",
                    name = "Kamel Restaurant",
                    username = "kamel",
                    phone = "0528242235",
                    rating = 4.9,
                    priceLevel = 3,
                    openingTime = "12:00",
                    closingTime = "23:00",
            ),
    )

}