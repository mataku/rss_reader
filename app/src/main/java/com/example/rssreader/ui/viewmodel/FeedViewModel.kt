package com.example.rssreader.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssreader.model.api.entity.HatenaRssItem
import com.example.rssreader.model.api.entity.presentation.onFailure
import com.example.rssreader.model.api.entity.presentation.onSuccess
import com.example.rssreader.model.api.repository.FeedRepository
import kotlinx.coroutines.launch

class FeedViewModel(private val feedRepository: FeedRepository) : ViewModel() {

    data class FeedUiModel(
        val rssItemList: List<HatenaRssItem> = emptyList(),
        val error: Throwable? = null
    )

    private val _feedLiveData = MutableLiveData<FeedUiModel>()
    val feedLiveData: LiveData<FeedUiModel> = _feedLiveData

    fun fetchFeed(category: String) {
        viewModelScope.launch {
            val result = feedRepository.fetch(category)
            result
                .onSuccess {
                    _feedLiveData.postValue(
                        FeedUiModel(rssItemList = it)
                    )
                }
                .onFailure {
                    _feedLiveData.postValue(
                        FeedUiModel(error = it)
                    )
                }
        }
    }
}