package org.thechance.api_gateway.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Trip(
    val clientId: String? = null,
    val startPoint: Location? = null,
    val destination: Location? = null,
    val price: Double? = null,
    val taxiId: String? = null,
    val driverId: String? = null,
    val rate: Double? = null,
    val startDate: String? = null,
    val endDate: String? = null,
)