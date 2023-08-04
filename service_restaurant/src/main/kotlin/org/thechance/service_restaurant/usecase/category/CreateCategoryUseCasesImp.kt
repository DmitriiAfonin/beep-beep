package org.thechance.service_restaurant.usecase.category

import org.koin.core.annotation.Single
import org.thechance.service_restaurant.data.gateway.CategoryGateway
import org.thechance.service_restaurant.entity.Category
import org.thechance.service_restaurant.utils.MissingProperties
import org.thechance.service_restaurant.utils.MultiErrorException

@Single
class CreateCategoryUseCasesImp(private val categoryGateway: CategoryGateway) :
    CreateCategoryUseCases {
    override suspend fun invoke(category: Category): Boolean {
        validate(category)
        return categoryGateway.addCategory(category)
    }

    private fun validate(category: Category) {
        val errorCodes = mutableListOf<Int>()
        if (category.name.isNullOrBlank()) {
            errorCodes.add(MissingProperties.code)
        }
        if (errorCodes.isNotEmpty()) {
            throw MultiErrorException(errorCodes)
        }
    }
}