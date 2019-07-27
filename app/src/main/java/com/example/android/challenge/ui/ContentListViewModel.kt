package com.example.android.challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.android.challenge.data.ContentRepository
import com.example.android.challenge.model.Content
import com.example.android.challenge.model.ContentResult

/**
 * ViewModel for the [ContentListActivity] screen.
 * The ViewModel works with the [ContentRepository] to get the data.
 */
class ContentListViewModel(private val repository: ContentRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<Boolean>()
    private val repoResult: LiveData<ContentResult> = Transformations.map(queryLiveData) {
        repository.getContent()
    }

    val repos: LiveData<PagedList<Content>> = Transformations.switchMap(repoResult) { it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it.networkErrors }

    /**
     * Search a repository based on a query string.
     */
    fun loadContent() {
        queryLiveData.postValue(true)
    }
}
