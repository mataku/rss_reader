package com.example.rssreader.model.api.repository

import com.example.rssreader.model.api.RSSClient
import com.example.rssreader.model.api.entity.HatenaRssItem
import com.example.rssreader.model.api.entity.extension.toNetworkError
import com.example.rssreader.model.api.entity.presentation.RequestResult
import com.example.rssreader.model.api.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FeedRepository(
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
    private val rssClient: RSSClient = RSSClient
) {
    suspend fun fetch(category: String): RequestResult<List<HatenaRssItem>> {
        return withContext(coroutineContext) {
            val service = rssClient.create(ApiService::class.java)
            val response = if (category.isEmpty()) {
                service.getOverallEntries()
            } else {
                service.getCategoryEntries(category = category)
            }
            val body = response.body()
            if (response.isSuccessful && body != null) {
                RequestResult.success(body.items)
            } else {
                RequestResult.failure(response.toNetworkError())
            }
        }
    }
}