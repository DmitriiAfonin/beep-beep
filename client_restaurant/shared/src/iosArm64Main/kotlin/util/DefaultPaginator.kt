package util

actual class DefaultPaginator<Key, Item> actual constructor(
    initialKey: Key,
    onLoadUpdated: (Boolean) -> Unit,
    onRequest: suspend (nextKey: Key) -> List<Item>,
    getNextKey: suspend (List<Item>) -> Key,
    onError: suspend (Throwable?) -> Unit,
    onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) {
    actual suspend fun loadNextItems() {
    }

    actual fun reset() {
    }

}