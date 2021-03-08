package com.example.rssreader.model.api.service

import com.example.rssreader.model.api.entity.HatenaRss
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/hotentry.rss")
    suspend fun getOverallEntries(): Response<HatenaRss>
}