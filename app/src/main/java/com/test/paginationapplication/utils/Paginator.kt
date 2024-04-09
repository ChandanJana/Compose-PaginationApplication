package com.test.paginationapplication.utils

/**
 * Created by Chandan Jana on 23-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
interface Paginator<Key, T> {
    suspend fun loadNextPage()
    fun reset()
}