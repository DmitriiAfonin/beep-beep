package org.thechance.common.domain.usecase

import org.jetbrains.skia.Pattern

interface IValidateRestaurantUseCase {
    fun validateRestaurantName(name: String): Boolean
    fun validateUserName(name: String): Boolean
    fun validateUserPassword(name: String): Boolean
    fun validateNumber(number: String): Boolean
    fun validateStartTime(hour: String): Boolean
    fun validateEndTime(hour: String): Boolean
    fun validateLocation(location: String): Boolean
}

class ValidateRestaurantUseCase():IValidateRestaurantUseCase{
    override fun validateRestaurantName(name: String): Boolean {
        return true    }

    override fun validateUserName(name: String): Boolean {
        return true    }

    override fun validateUserPassword(name: String): Boolean {
        return true    }

    override fun validateNumber(number: String): Boolean {
        return true
    }

    override fun validateStartTime(hour: String): Boolean {
        return true
    }

    override fun validateEndTime(hour: String): Boolean {
        return true
    }

    override fun validateLocation(location: String): Boolean {
//        val loc = Pattern.compile("^((-?|\\+?)?\\d+(\\.\\d+)?),\\s*((-?|\\+?)?\\d+(\\.\\d+)?)\$")
//        return location.isNotBlank() && loc.matcher(location).matches()
        return true
    }
}
