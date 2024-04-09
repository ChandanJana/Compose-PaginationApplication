package com.test.paginationapplication.model

import com.test.paginationapplication.model.ListItem

/**
 * Created by Chandan Jana on 23-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<ListItem> = emptyList(),
    val error: String? = null,
    val endReach: Boolean = false,
    val page: Int = 0
)