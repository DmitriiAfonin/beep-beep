package data.gateway.remote

import data.remote.model.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.HttpResponse
import presentation.base.InternetException
import presentation.base.InvalidCredentialsException
import presentation.base.InvalidPasswordException
import presentation.base.NoInternetException
import presentation.base.PermissionDenied
import presentation.base.UnknownErrorException
import presentation.base.UserAlreadyExistException
import presentation.base.UserNotFoundException

abstract class BaseRemoteGateway(val client: HttpClient) {

   protected suspend inline fun <reified T> tryToExecute(
        method: HttpClient.() -> HttpResponse
    ): T {
        try {
            return client.method().body()
        } catch (e: ClientRequestException) {
            val errorMessages = e.response.body<BaseResponse<*>>().status.errorMessages
            errorMessages?.let { throwMatchingException(it) }
            throw UnknownErrorException("")
        } catch (e: InternetException) {
            throw NoInternetException("")
        } catch (e: Exception) {
            throw UnknownErrorException("")
        }
    }

    fun throwMatchingException(errorMessages: Map<String, String>) {
        errorMessages.let {
            if (it.containsErrors(PASSWORD_CANNOT_BE_BLANK)) {
                throw InvalidPasswordException(it.getOrEmpty(PASSWORD_CANNOT_BE_BLANK))
            }
            if (it.containsErrors(USER_ALREADY_EXIST)) {
                throw UserAlreadyExistException(it.getOrEmpty(USER_ALREADY_EXIST))
            }
            if (it.containsErrors(USER_NOT_FOUND)) {
                throw UserNotFoundException(it.getOrEmpty(USER_NOT_FOUND))
            }
            if (it.containsErrors(INVALID_PERMISSION)) {
                throw PermissionDenied(it.getOrEmpty(""))
            }
            if (it.containsErrors(INVALID_CREDENTIALS)) {
                throw InvalidCredentialsException(it.getOrEmpty(INVALID_CREDENTIALS))
            } else {
                throw UnknownErrorException(it.getOrEmpty(""))
            }
        }
    }

    private fun Map<String, String>.containsErrors(vararg errorCodes: String): Boolean =
        keys.containsAll(errorCodes.toList())

    private fun Map<String, String>.getOrEmpty(key: String): String = get(key) ?: ""

    companion object {
        const val USER_ALREADY_EXIST = "1002"
        const val INVALID_USERNAME = "1003"
        const val PASSWORD_CANNOT_BE_LESS_THAN_8_CHARACTERS = "1005"
        const val USERNAME_CANNOT_BE_BLANK = "1006"
        const val PASSWORD_CANNOT_BE_BLANK = "1007"
        const val INVALID_CREDENTIALS = "1013"
        const val USER_NOT_FOUND = "1043"
        const val INVALID_PERMISSION = "8000"
    }
}