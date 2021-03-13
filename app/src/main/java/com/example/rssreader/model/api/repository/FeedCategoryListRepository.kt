package com.example.rssreader.model.api.repository

import com.example.rssreader.model.api.entity.FeedCategory
import com.example.rssreader.model.api.entity.presentation.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FeedCategoryListRepository(private val coroutineContext: CoroutineContext = Dispatchers.IO) {
    suspend fun fetchCategoryList(): RequestResult<List<FeedCategory>> {
        return try {
            withContext(context = coroutineContext) {
                // Mock feed category
                val categoryList = listOf(
                    FeedCategory("", "総合"),
                    FeedCategory("general", "一般"),
                    FeedCategory("social", "世の中"),
                    FeedCategory("economics", "政治と経済"),
                    FeedCategory("life", "暮らし"),
                    FeedCategory("knowledge", "学び"),
                    FeedCategory("it", "テクノロジー"),
                    FeedCategory("fun", "おもしろ"),
                    FeedCategory("entertainment", "エンタメ"),
                    FeedCategory("game", "アニメとゲーム")
                )
                RequestResult.success(categoryList)
            }
        } catch (e: Throwable) {
            RequestResult.failure(e)
        }
    }
}