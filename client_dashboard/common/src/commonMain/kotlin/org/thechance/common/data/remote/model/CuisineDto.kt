package org.thechance.common.data.remote.model

import kotlinx.serialization.SerialName


data class CuisineDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)
