package org.thechance.service_identity.data.collection.mappers

import org.thechance.service_identity.data.collection.AddressCollection
import org.thechance.service_identity.domain.entity.Address

fun AddressCollection.toEntity() = Address(
    id = id.toString(),
    location = location?.toEntity(),
    address = address ?: ""
)


fun List<AddressCollection>.toEntity(): List<Address> {
    return this.map { it.toEntity() }
}
