package com.example.rssreader.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssreader.model.api.entity.FeedCategory
import com.example.rssreader.model.api.entity.presentation.onFailure
import com.example.rssreader.model.api.entity.presentation.onSuccess
import com.example.rssreader.model.api.repository.FeedCategoryListRepository
import kotlinx.coroutines.launch

class MainViewModel(private val categoryListRepository: FeedCategoryListRepository) : ViewModel() {

    data class MainUiModel(
        val categoryList: List<FeedCategory> = emptyList(),
        val throwable: Throwable? = null
    )

    private val _categoryLiveData = MutableLiveData<MainUiModel>()
    val categoryLiveData = _categoryLiveData

    fun fetchCategoryList() {
        viewModelScope.launch {
            val result = categoryListRepository.fetchCategoryList()
            result
                .onSuccess {
                    _categoryLiveData.postValue(
                        MainUiModel(categoryList = it)
                    )
                }
                .onFailure {
                    _categoryLiveData.postValue(
                        MainUiModel(throwable = it)
                    )
                }
        }
    }
}