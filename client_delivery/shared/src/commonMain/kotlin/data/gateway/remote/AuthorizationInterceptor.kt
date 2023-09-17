package data.gateway.remote

//
//fun Scope.authorizationIntercept(client: HttpClient) {
//
//    client.plugin(HttpSend).intercept { request ->
//
////        val localConfigurationGateway = get<LocalConfigurationGateway>()
////        // todo add identity gate way to fix this error
////        val remoteIdentityGateway = get<IdentityRemoteGateway>()
////
////        val accessToken = localConfigurationGateway.getAccessToken()
////        val refreshToken = localConfigurationGateway.getRefreshToken()
////
////        request.headers {
////            append("Authorization", "Bearer $accessToken")
////        }
////        val originalCall = execute(request)
////
////        if (originalCall.response.status.value == 401) {
////            val (access, refresh) = remoteIdentityGateway.refreshAccessToken(refreshToken)
////            localConfigurationGateway.saveAccessToken(access)
////            localConfigurationGateway.saveRefreshToken(refresh)
////            execute(request)
////        } else {
////            originalCall
////        }
////    }
//
//}

