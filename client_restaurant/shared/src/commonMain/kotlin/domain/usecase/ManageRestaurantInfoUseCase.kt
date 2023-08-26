package domain.usecase

import domain.entity.Restaurant
import domain.gateway.ILocalGateWay
import domain.gateway.IRemoteGateWay

interface IManageRestaurantInfoUseCase {
    suspend fun getRestaurantInfo(): Restaurant?
    suspend fun updateRestaurantInfo(restaurant: Restaurant): Restaurant?

}

class ManageRestaurantInfoUseCase(
    private val remoteGateWay: IRemoteGateWay,
    private val localGateWay: ILocalGateWay
) :
    IManageRestaurantInfoUseCase {
    override suspend fun getRestaurantInfo(): Restaurant? {
        val restaurantId = localGateWay.getRestaurantId()
        return remoteGateWay.getRestaurantInfo(restaurantId)
    }

    override suspend fun updateRestaurantInfo(restaurant: Restaurant): Restaurant? {
        if (validateRestaurantInfo(restaurant)) {
            return remoteGateWay.updateRestaurantInfo(restaurant)
        }
        return null
    }

    private fun validateRestaurantInfo(restaurant: Restaurant): Boolean {
        return validateRestaurantName(restaurant.name)
                && validateRestaurantDescription(restaurant.description)
    }

    private fun validateRestaurantName(restaurantName: String): Boolean {
        return restaurantName.length in 4..25
    }

    private fun validateRestaurantDescription(description: String): Boolean {
        return description.length <= 255
    }
}