package org.thechance.service_location.domain.entity

import org.thechance.service_location.domain.util.Validation.Companion.LATITUDE_MAX
import org.thechance.service_location.domain.util.Validation.Companion.LATITUDE_MIN
import org.thechance.service_location.domain.util.Validation.Companion.LONGITUDE_MAX
import org.thechance.service_location.domain.util.Validation.Companion.LONGITUDE_MIN
import org.thechance.service_location.domain.util.exciptions.INVALID_LOCATION
import org.thechance.service_location.domain.util.exciptions.MultiErrorException

data class Location(
    val latitude: Double,
    val longitude: Double,
){

    init {
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw MultiErrorException(listOf(INVALID_LOCATION))
        }
        if (longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw MultiErrorException(listOf(INVALID_LOCATION))
        }
    }
}