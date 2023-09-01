package util.pagingsource

import androidx.paging.PagingSource
import domain.gateway.remote.IMealRemoteGateway


abstract class BasePagingSource<Value : Any> (
    protected val remoteGateway: IMealRemoteGateway
) : PagingSource<Int, Value>() {

     protected abstract suspend fun fetchData(page: Int): List<Value>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val currentPage = params.key ?: 1
        return try {
            val response = fetchData(currentPage)
            val nextKey = (currentPage + 1).takeIf { response.lastIndex >= currentPage }
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}