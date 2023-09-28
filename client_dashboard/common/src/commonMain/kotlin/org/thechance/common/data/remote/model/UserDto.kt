package org.thechance.common.data.remote.model

import kotlinx.serialization.SerialName

data class UserResponse(
    @SerialName("items") val users: List<UserDto>,
    @SerialName("total") val total: Int,
)

data class UserDto(
    @SerialName("id") val id: String,
    @SerialName("fullName") val fullName: String,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("permission") val permission: Int,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("country") val country: String = "",
)
