package org.thechance.service_identity.domain.usecases.useraccount

import org.koin.core.annotation.Single
import org.thechance.service_identity.domain.entity.Address
import org.thechance.service_identity.domain.gateway.DataBaseGateway

@Single
class UserAccountUseCaseImp(
    private val dataBaseGateway: DataBaseGateway
) : UserAccountUseCase {

    // region user

    // endregion

    // region address
    override suspend fun addAddress(address: Address): Boolean {
        return dataBaseGateway.addAddress(address)
    }

    override suspend fun deleteAddress(id: String): Boolean {
        return dataBaseGateway.deleteAddress(id)
    }

    override suspend fun updateAddress(id: String, address: Address): Boolean {
        return dataBaseGateway.updateAddress(id, address)
    }

    override suspend fun getAddress(id: String): Address {
        return dataBaseGateway.getAddress(id) ?: throw Throwable()
    }

    override suspend fun getUserAddresses(userId: String): List<Address> {
        return dataBaseGateway.getUserAddresses(userId)
    }

    //endregion
}