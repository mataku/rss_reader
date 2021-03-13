package com.example.rssreader.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rssreader.model.api.repository.FeedCategoryListRepository
import com.example.rssreader.ui.viewmodel.MainViewModel

class MainViewModelFactory(
    private val categoryListRepository: FeedCategoryListRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(categoryListRepository) as T
        } else {
            throw IllegalArgumentException("${modelClass.name} is unknown")
        }
    }
}