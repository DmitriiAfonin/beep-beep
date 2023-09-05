package org.thechance.service_location.domain.util

import org.thechance.service_location.domain.util.exciptions.INVALID_ID
import org.thechance.service_location.domain.util.exciptions.MultiErrorException


interface IValidation {
    fun isValidName(name: String?): Boolean
    fun isValidId(id: String?): Boolean
    fun isValidLatitude(latitude: Double): Boolean
    fun isValidLongitude(longitude: Double): Boolean
    fun isValidLocation(latitude: Double, longitude: Double): Boolean
    fun checkIsValidIds(id: String, listIds: List<String>)
    fun isValidAddress(address: String): Boolean

}


class Validation : IValidation  {

    override fun isValidName(name: String?): Boolean {
        return name != null && name.matches(Regex("^[A-Za-z0-9\\s\\[\\]\\(\\)\\-.,&]{4,$NAME_MAX_LENGTH}$"))
    }

    override fun isValidId(id: String?): Boolean {
        val objectIdPattern = "[0-9a-fA-F]{24}"
        return id != null && id.matches(Regex(objectIdPattern))
    }

    internal fun isValidIds(ids: List<String>?): Boolean {
        return !ids.isNullOrEmpty() && ids.all { isValidId(it) }
    }

    override fun isValidLatitude(latitude: Double): Boolean {
        return (latitude != -1.0) && (latitude in LATITUDE_MIN..LATITUDE_MAX)
    }

    override fun isValidLongitude(longitude: Double): Boolean {
        return (longitude != -1.0) && (longitude in LONGITUDE_MIN..LONGITUDE_MAX)
    }

    override fun isValidLocation(latitude: Double, longitude: Double): Boolean {
        return isValidLatitude(latitude) && isValidLongitude(longitude)
    }

    override fun isValidAddress(address: String): Boolean {
        val regex = Regex("[0-9]+ [a-zA-Z]+ [a-zA-Z]+") // Example pattern: "123 Main Street"
        return regex.matches(address)
    }




    companion object {
        const val DESCRIPTION_MAX_LENGTH = 255
        const val DESCRIPTION_MIN_LENGTH = 20
        const val NAME_MAX_LENGTH = 50
        const val LATITUDE_MIN = -90.0
        const val LATITUDE_MAX = 90.0
        const val LONGITUDE_MIN = -180.0
        const val LONGITUDE_MAX = 180.0
        const val NULL_DOUBLE = -1.0
        const val MAX_CUISINE = 3
        const val PAGINATION_MAX_LIMIT = 100
    }
}
