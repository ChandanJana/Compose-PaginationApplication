package com.test.paginationapplication.utils

/**
 * Created by Chandan Jana on 23-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

// Here key represent the Page number and T represent the Item of List
class DefaultPaginator<Key, T>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<T>>,
    private inline val getNextKey: suspend (List<T>) -> Key,
    private inline val onError: suspend (Throwable) -> Unit,
    private inline val onSuccess: suspend (items: List<T>, newKey: Key) -> Unit,
): Paginator<Key, T> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    override suspend fun loadNextPage() {

        if (isMakingRequest)
            return

        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)

    }

    override fun reset() {
        currentKey = initialKey
    }
}