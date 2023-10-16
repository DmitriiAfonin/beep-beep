package domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import domain.entity.Order
import domain.gateway.IOrderGateway
import kotlinx.coroutines.flow.Flow

interface IPaginationTestUseCase {
    suspend fun getPaginationTest(): Flow<PagingData<Order>>
}

class PaginationTestUseCase(
    private val pagingSource: MyPagingSource
) : IPaginationTestUseCase {
    override suspend fun getPaginationTest(): Flow<PagingData<Order>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {  pagingSource }
        ).flow
    }
}

class MyPagingSource( private val orderGateway: IOrderGateway) : PagingSource<Int, Order>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Order> {
        val page = params.key ?: 1
        return try {
            val result = orderGateway.getOrderHistory(page)
            LoadResult.Page(
                data = result,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (result.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Order>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}