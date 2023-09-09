package util

expect class DefaultPaginator<Key, Item>(
    initialKey: Key,
    onLoadUpdated: (Boolean) -> Unit,
    onRequest: suspend (nextKey: Key) -> List<Item>,
    getNextKey: suspend (List<Item>) -> Key,
    onError: suspend (Throwable?) -> Unit,
    onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) {
    suspend fun loadNextItems()
    fun reset()

}