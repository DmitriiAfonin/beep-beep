package org.thechance.service_restaurant.usecase.category

import org.koin.core.annotation.Single
import org.thechance.service_restaurant.data.gateway.CategoryGateway
import org.thechance.service_restaurant.entity.Category
import org.thechance.service_restaurant.utils.Error
import org.thechance.service_restaurant.utils.ErrorCodes
import org.thechance.service_restaurant.utils.MultiErrorException

@Single
class CreateCategoryUseCasesImp(private val categoryGateway: CategoryGateway) :
    CreateCategoryUseCases {
    override suspend fun invoke(category: Category): Boolean {
        validate(category)
        return categoryGateway.addCategory(category)
    }

    private fun validate(category: Category) {
        val errorCodes = mutableListOf<Error>()
        if (category.name.isNullOrBlank()) {
            errorCodes.add(
                Error(
                    errorCode = ErrorCodes.MissingProperties.code,
                    errorMessage = "enter valid name"
                )
            )

        }
        if (errorCodes.isNotEmpty()) {
            throw MultiErrorException(errorCodes)
        }
    }
}