package domain.gateway.remote


interface IRemoteIdentityGateway {

    suspend fun loginUser(userName: String, password: String): Pair<String, String>
    suspend fun requestPermission(
        restaurantName: String,
        ownerEmail: String,
        cause: String
    ): Boolean


}