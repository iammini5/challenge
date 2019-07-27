

package com.example.android.challenge.data

import androidx.paging.LivePagedListBuilder
import com.example.android.challenge.api.ContentsService
import com.example.android.challenge.db.ContentsLocalCache
import com.example.android.challenge.model.ContentBoundaryCallback
import com.example.android.challenge.model.ContentResult

/**
 * Repository class that works with local and remote data sources.
 */
class ContentRepository(
        private val service: ContentsService,
        private val cache: ContentsLocalCache
) {

    fun getContent(): ContentResult {
        // Get data source factory from the local cache
        val dataSourceFactory = cache.contentsByTime()

        // Construct the boundary callback
        val boundaryCallback = ContentBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return ContentResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 1
    }
}
