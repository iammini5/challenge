
package com.example.android.challenge.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * ContentResult from a getContent, which contains LiveData<List<Repo>> holding query data,
 * and a LiveData<String> of network error state.
 */
data class ContentResult(
        val data: LiveData<PagedList<Content>>,
        val networkErrors: LiveData<String>
)
