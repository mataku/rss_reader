package com.example.rssreader.model.api.service

import com.example.rssreader.model.api.entity.HatenaRss
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/hotentry.rss")
    suspend fun getOverallEntries(): Response<HatenaRss>

    @GET("/hotentry/{category}.rss")
    suspend fun getCategoryEntries(
        @Path("category") category: String
    ): Response<HatenaRss>
}