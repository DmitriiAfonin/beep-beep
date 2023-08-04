package org.thechance.service_restaurant.utils

data class MultiErrorException(val errorCodes: List<Int>) : Throwable()

sealed class ErrorCodes(val code: Int)

object IdNotValid : ErrorCodes(1000)
class GeneralException : ErrorCodes(1009)
object NotFoundException : ErrorCodes(1002)
object DeleteCategoryException : ErrorCodes(1003)
object MissingProperties  : ErrorCodes(1004)