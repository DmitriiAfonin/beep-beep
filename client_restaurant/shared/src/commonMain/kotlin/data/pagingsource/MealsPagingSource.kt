package data.pagingsource

import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingState
import domain.entity.Meal
import domain.gateway.remote.IMealRemoteGateway

/**
 * Created by Aziza Helmy on 9/1/2023.
 */
//todo ask for meals by cuisine id here?
class MealsPagingSource(
    private val remoteGateway: IMealRemoteGateway,
    private val restaurantId: String
) : BasePagingSource<Meal>() {
    override suspend fun fetchData(page: Int, limit: Int): List<Meal> {
        return remoteGateway.getAllMealsByRestaurantId(restaurantId, page, limit)
    }

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Meal> {
        TODO("Not yet implemented")
    }

    override fun getRefreshKey(state: PagingState<Int, Meal>): Int? {
        TODO("Not yet implemented")
    }

}