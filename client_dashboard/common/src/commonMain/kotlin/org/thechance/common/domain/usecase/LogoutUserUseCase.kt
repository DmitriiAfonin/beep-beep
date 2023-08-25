package org.thechance.common.domain.usecase

import org.thechance.common.domain.getway.ILocalDataGateway

interface ILogoutUserUseCase {

    suspend fun logoutUser()

}

class LogoutUserUseCase(private val localGateway: ILocalDataGateway): ILogoutUserUseCase{

    override suspend fun logoutUser() {
        localGateway.clearTokens()
    }

}