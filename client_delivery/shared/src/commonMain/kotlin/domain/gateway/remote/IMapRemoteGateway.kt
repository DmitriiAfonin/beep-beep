package domain.gateway.remote

import data.remote.model.LocationDto
import domain.entity.Order
import kotlinx.coroutines.flow.Flow


interface IMapRemoteGateway {
    suspend fun getOrders(): Flow<Order>
    suspend fun sendLocation(location: LocationDto, tripId: String)
    suspend fun updateTrip(taxiId: String, tripId: String): Order
}