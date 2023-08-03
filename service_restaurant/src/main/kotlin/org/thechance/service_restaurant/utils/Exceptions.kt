package org.thechance.service_restaurant.utils

import kotlinx.serialization.Serializable

object IdNotValid : Throwable("1000")

object NotFoundException : Throwable("1001")
object DeleteCategoryException : Throwable("1001")
class GeneralException : Throwable()

data class MultiErrorException(val errorCodes: List<Error>) : Throwable()

@Serializable
data class Error(
    val errorMessage: String,
    val errorCode: Int,
)

sealed class ErrorCodes(val code: Int) {
    object MissingProperties : ErrorCodes(1002)
}