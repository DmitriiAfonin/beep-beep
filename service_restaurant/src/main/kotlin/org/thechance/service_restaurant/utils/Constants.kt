package org.thechance.service_restaurant.utils

const val DATABASE_NAME = "TheChanceBeepBeep"

object Validations {
    fun isValidId(id: String) = "^[0-9A-Fa-f]{24}\$".toRegex().matches(id)
}

