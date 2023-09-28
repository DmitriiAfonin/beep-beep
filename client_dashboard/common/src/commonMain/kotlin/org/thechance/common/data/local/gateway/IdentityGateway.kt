package org.thechance.common.data.local.gateway

//import io.realm.kotlin.Realm
//import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
//import org.thechance.common.data.local.model.ConfigurationCollection
import org.thechance.common.domain.getway.IIdentityGateway

class IdentityGateway() : IIdentityGateway {

    override suspend fun createUserConfiguration() {
//        realm.write { copyToRealm(ConfigurationCollection().apply { id = CONFIGURATION_ID }) }
    }

    override suspend fun refreshAccessToken(refreshToken: String): Pair<String, String> {
        return Pair("", "")
    }

    override suspend fun saveAccessToken(token: String) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.accessToken = token
//        }
    }

    override suspend fun saveRefreshToken(token: String) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.refreshToken = token
//        }
    }

    override suspend fun getAccessToken(): String {
//        return realm.query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.accessToken ?: ""
        return ""
    }

    override suspend fun getRefreshToken(): String {
//        return realm.query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.refreshToken ?: ""
        return ""
    }

    override suspend fun clearConfiguration() {
//        realm.write { delete(query<ConfigurationCollection>()) }
    }

    override suspend fun shouldUserKeptLoggedIn(keepLoggedIn: Boolean) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.keepLoggedIn = keepLoggedIn
//        }
    }

    override suspend fun isUserKeptLoggedIn(): Boolean {
//        return realm.query<ConfigurationCollection>(
//            "$ID == $CONFIGURATION_ID"
//        ).first().find()?.keepLoggedIn ?: false
        return true
    }

    override suspend fun getThemeMode(): Flow<Boolean> {
//        return realm.query<ConfigurationCollection>(
//            "$ID == $CONFIGURATION_ID"
//        ).asFlow().map { result -> result.list.find { it.isDarkMode }?.isDarkMode ?: false }
        return flow { false }
    }

    override suspend fun updateThemeMode(mode: Boolean) {
//        realm.write {
//            query<ConfigurationCollection>("$ID == $CONFIGURATION_ID").first().find()?.isDarkMode = mode
//        }
    }


    companion object {
        private const val CONFIGURATION_ID = 0
        private const val ID = "id"
    }

}