package org.thechance.common.data.gateway.remote.model

import kotlinx.serialization.SerialName

data class PaginationResponse<T>(
    @SerialName("items") val items: List<T>,
    @SerialName("total") val total: Int,
)