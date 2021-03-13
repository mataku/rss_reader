package com.example.rssreader.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rssreader.model.api.repository.FeedRepository
import com.example.rssreader.ui.viewmodel.FeedViewModel

class FeedViewModelFactory(
    private val feedRepository: FeedRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(feedRepository = feedRepository) as T
        } else {
            throw IllegalArgumentException("${modelClass.name} is unknown")
        }
    }
}