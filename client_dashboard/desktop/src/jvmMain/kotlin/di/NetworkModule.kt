package di

import io.ktor.client.HttpClient
import io.ktor.client.engine.ProxyBuilder
import io.ktor.client.engine.apache5.Apache5
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.engine.http
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.thechance.common.domain.getway.IUserLocalGateway
import java.io.FileInputStream
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


val NetworkModule = module {

    single {
        val client = HttpClient(Apache5) {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate>? = null
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit
            })

            engine {
                proxy = ProxyBuilder.http("http://192.168.0.106:8888")
                sslContext = SSLContext.getInstance("SSL").apply {
                        init(null, trustAllCerts, SecureRandom())
                    }
            }

            expectSuccess = true
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
                level = LogLevel.HEADERS
            }
            defaultRequest {
                url("https://beep-beep-api-gateway-nap2u.ondigitalocean.app")
                header("Application-Id", "3000")
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                gson()
            }
        }
        intercept(client)
        client
    }

    single(named("locationClient")) {
        val client = HttpClient(CIO) {
            expectSuccess = true
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
                level = LogLevel.HEADERS
            }
            install(ContentNegotiation) {
                gson()
            }
        }
        client
    }
}

fun Scope.intercept(client: HttpClient) {
    client.plugin(HttpSend).intercept { request ->

        val identityGateway = get<IUserLocalGateway>()

        val accessToken = identityGateway.getAccessToken()
        val refreshToken = identityGateway.getRefreshToken()
        val countryCode = identityGateway.getCountryCode()

        request.headers {
            append("Authorization", "Bearer $accessToken")
            append("Accept-Language", countryCode)
            append("Country-Code", countryCode)
        }
        val originalCall = execute(request)

        if (originalCall.response.status.value == 401) {
            val (access, refresh) = identityGateway.refreshAccessToken(refreshToken)
            identityGateway.saveAccessToken(access)
            identityGateway.saveRefreshToken(refresh)
            execute(request)
        } else {
            originalCall
        }
    }
}