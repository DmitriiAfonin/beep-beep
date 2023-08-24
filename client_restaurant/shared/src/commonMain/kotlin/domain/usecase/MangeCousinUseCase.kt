package domain.usecase

import domain.entity.Cuisine
import domain.entity.Meal
import domain.entity.Restaurant
import domain.gateway.IRemoteGateWay

interface IMangeCousinUseCase {
    suspend fun getCuisine(restaurantId: String): List<Cuisine>
    suspend fun getCuisine(): List<Cuisine>
    suspend fun getMealsByCuisineId(id: String): List<Meal>
}

class MangeCousinUseCase(private val remoteGateWay: IRemoteGateWay) : IMangeCousinUseCase {
    override suspend fun getCuisine(restaurantId: String): List<Cuisine> {
        return remoteGateWay.getCuisine(restaurantId)
    }

    override suspend fun getCuisine(): List<Cuisine> {
        return remoteGateWay.getCuisines()
    }

    override suspend fun getMealsByCuisineId(id: String): List<Meal> {
        return remoteGateWay.getMealsByCuisineId(id)
    }

}
