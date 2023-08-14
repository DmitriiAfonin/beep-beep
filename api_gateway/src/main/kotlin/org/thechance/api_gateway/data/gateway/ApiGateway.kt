package org.thechance.api_gateway.data.gateway

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import org.koin.core.annotation.Single
import org.thechance.api_gateway.data.model.APIS
import org.thechance.api_gateway.data.model.Taxi
import org.thechance.api_gateway.data.model.Trip
import org.thechance.api_gateway.domain.gateway.IApiGateway


@Single(binds = [IApiGateway::class])
class ApiGateway(
    private val client: HttpClient,
    private val clientAttributes: Attributes
) : IApiGateway {

    // region restaurant

    // endregion

    // region taxi api
    // region taxi
    override suspend fun addTaxi(taxi: Taxi) =
        tryToExecute<Taxi>(
            api = APIS.TAXI_API,
            urlString = "/taxi",
        ) {
            post(it) {
                contentType(ContentType.Application.Json)
                setBody(taxi)
            }
        }

    override suspend fun getTaxis(page: Int, limit: Int): List<Taxi> =
        tryToExecute<List<Taxi>>(
            api = APIS.TAXI_API,
            urlString = "/taxi",
        ) {
            get(it) {
                parameter("page", page)
                parameter("limit", limit)
            }
        }

    override suspend fun getTaxiById(taxiId: String): Taxi =
        tryToExecute<Taxi>(
            api = APIS.TAXI_API,
            urlString = "/taxi/${taxiId}",
        ) { get(it) }

    override suspend fun deleteTaxi(taxiId: String): Taxi =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/taxi/${taxiId}",
        ) { delete(it) }


    // endregion : taxi

    // region trip
    override suspend fun addTrip(trip: Trip): Trip =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip"
        ) {
            post(it) {
                setBody(trip)
            }
        }

    override suspend fun getTripById(tripId: String): Trip? =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip/${tripId}"
        ) {
            get(it)
        }


    override suspend fun getAllTrips(page: Int, limit: Int): List<Trip> =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip"
        ) {
            get(it) {
                parameter("page", page)
                parameter("limit", limit)
            }
        }

    override suspend fun getDriverTripsHistory(driverId: String, page: Int, limit: Int): List<Trip> =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip/driver/${driverId}"
        ) {
            get(it) {
                parameter("page", page)
                parameter("limit", limit)
            }
        }

    override suspend fun getClientTripsHistory(clientId: String, page: Int, limit: Int): List<Trip> =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip/driver/${clientId}"
        ) {
            get(it) {
                parameter("page", page)
                parameter("limit", limit)
            }
        }

    override suspend fun deleteTrip(tripId: String): Trip? =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip/${tripId}"
        ) {
            delete(it)
        }

    override suspend fun approveTrip(tripId: String, taxiId: String, driverId: String): Trip? =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip/${tripId}/approve"
        ) {
            put(it) {
                parameter("tripId", tripId)
                parameter("driverId", driverId)
                parameter("taxiId", taxiId)
            }
        }

    override suspend fun finishTrip(tripId: String, driverId: String): Trip?=
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip/${tripId}/finish"
        ) {
            put(it) {
                parameter("tripId", tripId)
                parameter("driverId", driverId)
            }
        }

    override suspend fun rateTrip(tripId: String, rate: Double): Trip? =
        tryToExecute(
            api = APIS.TAXI_API,
            urlString = "/trip/${tripId}/finish"
        ) {
            put(it) {
                parameter("tripId", tripId)
            }
        }


    // endregion : trip
    // endregion : taxi api

    // region identity

    // endregion

    // region notification

    // endregion

    private suspend inline fun <reified T> tryToExecute(
        api: APIS,
        urlString: String,
        method: HttpClient.(urlString: String) -> HttpResponse
    ): T {
        try {
            clientAttributes.put(AttributeKey("API"), api.value)
            val response = client.method(urlString)
            return response.body<T>()
        } catch (e: Throwable) {
            throw e
        }
    }

}
