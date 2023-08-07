package org.thechance.service_identity.api.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String? = null,
    val fullName: String? = null,
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val wallet: WalletDto? = null,
    val addresses: List<String> = emptyList(),
    val permissions: List<Int> = emptyList()
)

