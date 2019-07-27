
package com.example.android.challenge

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.android.challenge.api.ContentsService
import com.example.android.challenge.data.ContentRepository
import com.example.android.challenge.db.ContentsLocalCache
import com.example.android.challenge.db.ContentDatabase
import com.example.android.challenge.ui.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [ContentsLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): ContentsLocalCache {
        val database = ContentDatabase.getInstance(context)
        return ContentsLocalCache(database.contentsDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [ContentRepository] based on the [ContentsService] and a
     * [ContentsLocalCache]
     */
    private fun provideGithubRepository(context: Context): ContentRepository {
        return ContentRepository(ContentsService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}
