package data.remote

import data.remote.service.ApiService
import domain.entity.Tokens
import domain.gateway.IRemoteGateway

class RemoteGateway(
    private val apiService: ApiService
) : IRemoteGateway {
    override suspend fun createUser(fullName: String, username: String, password: String, email: String): Boolean {
        return apiService.createUser(fullName, username, password, email)
    }

    override suspend fun loginUser(userName: String, password: String): Tokens {
        return apiService.loginUser(userName, password)
    }
}