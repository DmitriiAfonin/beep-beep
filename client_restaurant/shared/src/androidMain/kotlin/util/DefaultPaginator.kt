package util

actual class DefaultPaginator<Key, Item> actual constructor(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> List<Item>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    actual override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)

        try {
            val items = onRequest(currentKey)
            currentKey = getNextKey(items)
            onSuccess(items, currentKey)
        } catch (e: Throwable) {
            onError(e)
        } finally {
            isMakingRequest = false
            onLoadUpdated(false)
        }
    }

    actual override fun reset() {
        currentKey = initialKey
    }
}