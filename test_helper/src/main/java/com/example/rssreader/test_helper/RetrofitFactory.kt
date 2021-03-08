package com.example.rssreader.test_helper

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitFactory {
    fun mock(mockWebServer: MockWebServer): Retrofit {
        val clientBuilder = okhttp3.OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.interceptors().add(httpLoggingInterceptor)

        return Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }
}
