package org.thechance.service_location.domain.util.exciptions

class MultiErrorException(val errorCodes: List<Int>) : Throwable(errorCodes.toString())

const val INVALID_LOCATION = 2003
