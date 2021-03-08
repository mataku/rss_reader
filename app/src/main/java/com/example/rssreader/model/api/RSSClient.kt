package com.example.rssreader.model.api

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RSSClient {
    private const val BASE_URL = "https://b.hatena.ne.jp"

    val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.instance)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T {
        return client.create(service)
    }
}