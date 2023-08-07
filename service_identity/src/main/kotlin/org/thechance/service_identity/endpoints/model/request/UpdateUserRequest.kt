package org.thechance.service_identity.endpoints.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val fullName: String,
    val username: String,
    val password: String,
    val email: String,
)