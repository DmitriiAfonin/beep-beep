package org.thechance.api_gateway.domain.gateway

import org.thechance.api_gateway.data.model.Taxi
import org.thechance.api_gateway.data.model.Trip

interface IApiGateway {
    // region taxi api
    // region taxi
    suspend fun getTaxis(page: Int, limit: Int): List<Taxi>

    suspend fun addTaxi(taxi: Taxi): Taxi

    suspend fun getTaxiById(taxiId: String): Taxi?

    suspend fun deleteTaxi(taxiId: String): Taxi

    // endregion taxi
    // region trip
    suspend fun addTrip(trip: Trip): Trip
    suspend fun getTripById(tripId: String): Trip?
    suspend fun getAllTrips(page: Int, limit: Int): List<Trip>
    suspend fun getDriverTripsHistory(
        driverId: String,
        page: Int,
        limit: Int
    ): List<Trip>

    suspend fun getClientTripsHistory(
        clientId: String,
        page: Int,
        limit: Int
    ): List<Trip>

    suspend fun deleteTrip(tripId: String): Trip?
    suspend fun approveTrip(tripId: String, taxiId: String, driverId: String): Trip?

    suspend fun finishTrip(tripId: String, driverId: String): Trip?

    suspend fun rateTrip(tripId: String, rate: Double): Trip?
    // endregion trip
    // endregion taxi api
}