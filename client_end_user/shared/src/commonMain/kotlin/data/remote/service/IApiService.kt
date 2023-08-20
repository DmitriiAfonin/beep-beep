package data.remote.service


import domain.entity.Tokens

interface IApiService {

    suspend fun createUser(
        fullName: String,
        username: String,
        password: String,
        email: String
    ) : Boolean

    suspend fun loginUser(
        userName: String,
        password: String,
    ): Tokens

}