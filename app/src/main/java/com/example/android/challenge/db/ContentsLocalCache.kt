
package com.example.android.challenge.db

import android.util.Log
import androidx.paging.DataSource
import com.example.android.challenge.model.Content
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class ContentsLocalCache(
        private val contentDao: ContentDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(contents: List<Content>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("ContentsLocalCache", "inserting ${contents.size} repos")
            contentDao.insert(contents)
            insertFinished()
        }
    }

    fun contentsByTime(): DataSource.Factory<Int, Content> {
        // appending '%' so we can allow other characters to be before and after the query string
        return contentDao.contentsByTime()
    }
}
