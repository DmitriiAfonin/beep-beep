package org.thechance.service_restaurant.usecase.category

import org.koin.core.annotation.Single
import org.thechance.service_restaurant.data.gateway.CategoryGateway
import org.thechance.service_restaurant.entity.Category
import org.thechance.service_restaurant.utils.isValidId

@Single
class GetCategoryDetailsUseCaseImp(
    private val categoryGateway: CategoryGateway
) : GetCategoryDetailsUseCase {
    override suspend fun invoke(categoryId: String): Category {
        if (!isValidId(categoryId)) {
            throw Throwable("Id Not Valid")
        }
        return categoryGateway.getCategory(categoryId) ?: throw Throwable("Not Found")
    }
}