package org.thechance.service_identity.data.mappers

import org.bson.types.ObjectId
import org.thechance.service_identity.api.model.UserDto
import org.thechance.service_identity.data.collection.UserCollection
import org.thechance.service_identity.data.collection.UserDetailsCollection
import org.thechance.service_identity.domain.entity.User
import org.thechance.service_identity.domain.entity.Wallet

fun UserCollection.toEntity(): User {
    return User(
        id = id.toHexString(),
        fullName = fullName,
        username = username,
        password = password,
        email = "",
        wallet = Wallet("", "", 0.0)
    )
}

fun UserDto.toEntity(): User {
    return User(
        id = id ?: "",
        fullName = fullName ?: "",
        username = username ?: "",
        password = password ?: "",
        email = email ?: "",
        wallet = wallet!!.toEntity()
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        id = id,
        fullName = fullName,
        username = username,
        password = password,
    )
}

fun List<User>.toDto(): List<UserDto> {
    return map { it.toDto() }
}

fun User.toCollection(): UserCollection {
    return UserCollection(
        fullName = this.fullName,
        username = this.username,
        password = this.password,
    )
}

fun User.toDetailsCollection(userId: String): UserDetailsCollection {
    return UserDetailsCollection(
        userId = ObjectId(userId),
        email = email,
        walletCollection = wallet.toCollection(),
        addresses = addresses.map { ObjectId(it) },
        permissions = permissions.map { it }
    )
}

