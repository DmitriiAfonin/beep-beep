package domain.usecase

import data.remote.model.LocationDto
import domain.entity.Order
import domain.gateway.remote.IMapRemoteGateway
import kotlinx.coroutines.flow.Flow

interface IManageOrderUseCase {
    suspend fun getOrders(): Flow<Order>
    suspend fun sendLocation(location: LocationDto, tripId: String)
    suspend fun updateTrip(taxiId: String, tripId: String): Order
}

class ManageOrderUseCase(private val remoteGateway: IMapRemoteGateway) : IManageOrderUseCase {
    override suspend fun getOrders(): Flow<Order> {
        println("getOrders")
        return remoteGateway.getOrders()
    }

    override suspend fun sendLocation(location: LocationDto, tripId: String) {
        remoteGateway.sendLocation(location, tripId)
    }

    override suspend fun updateTrip(taxiId: String, tripId: String): Order {
        return remoteGateway.updateTrip(taxiId, tripId)
    }


}