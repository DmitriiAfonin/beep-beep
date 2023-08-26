package data.local.gateway

import data.local.model.FlagDto
import data.local.model.TokenDto
import domain.gateway.ILocalGateWay
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class LocalGateWay(private val realm: Realm) : ILocalGateWay {

    override suspend fun saveAccessToken(token: String) {
        realm.write {
            copyToRealm(TokenDto().apply {
                this.accessToken = token
            })
        }
    }

    override suspend fun getAccessToken(): String {
        return realm.query<TokenDto>().first().find()?.accessToken
            ?: throw Exception("Access Token not found")
    }

    override suspend fun saveRefreshToken(token: String) {
        realm.write {
            copyToRealm(TokenDto().apply {
                this.refreshToken = token
            })
        }
    }

    override suspend fun getRefreshToken(): String {
        return realm.query<TokenDto>().first().find()?.refreshToken
            ?: throw Exception("Refresh Token not found")
    }

    override suspend fun saveKeepMeLoggedInFlag(isChecked: Boolean) {
        realm.write {
            copyToRealm(FlagDto().apply {
                this.isKeepMeLoggedInMeChecked = isChecked
            })
        }
    }

    override suspend fun getKeepMeLoggedInFlag(): Boolean {
        return realm.query<FlagDto>().first().find()?.isKeepMeLoggedInMeChecked
            ?: throw Exception("Nothing ..")
    }

    override suspend fun saveRestaurantId(restaurantId: String) {
        realm.write {
            copyToRealm(FlagDto().apply {
                this.restaurantId = restaurantId
            })
        }
    }

    override suspend fun getRestaurantId(): String {
        return realm.query<FlagDto>().first().find()?.restaurantId
            ?: throw Exception("Nothing ..")
    }


}