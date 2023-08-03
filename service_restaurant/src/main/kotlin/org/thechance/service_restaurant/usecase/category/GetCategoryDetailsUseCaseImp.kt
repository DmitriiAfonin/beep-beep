package org.thechance.service_restaurant.usecase.category

import org.koin.core.annotation.Single
import org.thechance.service_restaurant.data.gateway.CategoryGateway
import org.thechance.service_restaurant.entity.Category
import org.thechance.service_restaurant.utils.NotFoundException
import org.thechance.service_restaurant.utils.IdNotValid
import org.thechance.service_restaurant.utils.Validations

@Single
class GetCategoryDetailsUseCaseImp(
    private val categoryGateway: CategoryGateway
) : GetCategoryDetailsUseCase {
    override suspend fun invoke(categoryId: String): Category {
        if (!Validations.isValidId(categoryId)) {
            throw IdNotValid
        }
        return categoryGateway.getCategory(categoryId) ?: throw NotFoundException
    }
}