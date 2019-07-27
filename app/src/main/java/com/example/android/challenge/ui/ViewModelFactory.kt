
package com.example.android.challenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.challenge.data.ContentRepository

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val repository: ContentRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContentListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContentListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
