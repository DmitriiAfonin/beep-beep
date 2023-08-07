package org.thechance.service_identity.endpoints.model

import kotlinx.serialization.Serializable
import org.thechance.service_identity.api.model.WalletDto

@Serializable
data class DetailedUserDto(
    val id: String? = null,
    val fullName: String? = null,
    val username: String? = null,
    val password: String? = null,
    val wallet: WalletDto? = null,
    val addresses: List<String> = emptyList(),
    val permissions: List<Int> = emptyList()
)