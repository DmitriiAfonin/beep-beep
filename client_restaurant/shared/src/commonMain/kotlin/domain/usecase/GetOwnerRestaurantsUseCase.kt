package domain.usecase

import domain.entity.Restaurant
import domain.gateway.remote.IRemoteRestaurantGateway

interface IGetOwnerRestaurantsUseCase {
    suspend fun getOwnerRestaurants(): List<Restaurant>
}

class GetOwnerRestaurantsUseCase(private val remoteRestaurantGateway: IRemoteRestaurantGateway) :
    IGetOwnerRestaurantsUseCase {

    // todo: need to know where the id or the token will do in that case
    override suspend fun getOwnerRestaurants(): List<Restaurant> {
        return remoteRestaurantGateway.getRestaurantsByOwnerId("", "")
            .sortedByDescending { it.isRestaurantOpen() }
    }

}

