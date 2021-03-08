package com.example.rssreader.model.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClient {
    val instance = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS).build()
}