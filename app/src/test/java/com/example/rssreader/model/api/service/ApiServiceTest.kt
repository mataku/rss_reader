package com.example.rssreader.model.api.service

import com.example.rssreader.model.api.entity.HatenaRss
import com.example.rssreader.test_helper.AssetsHelper
import com.example.rssreader.test_helper.RetrofitFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class ApiServiceTest {

    private val mockWebServer = MockWebServer()

    @BeforeTest
    fun setUp() {
        mockWebServer.start()
    }

    @AfterTest
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getOverallEntries() = runBlocking {
        val dispatcher = object : okhttp3.mockwebserver.Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path == "/hotentry.rss") {
                    val body = AssetsHelper.getAssetFileStrings("hotentry.xml")
                    MockResponse().setResponseCode(200)
                        .setBody(body)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }
        mockWebServer.dispatcher = dispatcher

        val mock = RetrofitFactory.mock(mockWebServer)
        val response = mock.create(ApiService::class.java).getOverallEntries()
        assertTrue(response.isSuccessful)
        val body = response.body()
        assertTrue(body is HatenaRss)
    }

    @Test
    fun getCategoryEntries() = runBlocking {
        val category = "social"

        val dispatcher = object : okhttp3.mockwebserver.Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path == "/hotentry/${category}.rss") {
                    val body = AssetsHelper.getAssetFileStrings("hotentry.xml")
                    MockResponse().setResponseCode(200)
                        .setBody(body)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }
        mockWebServer.dispatcher = dispatcher

        val mock = RetrofitFactory.mock(mockWebServer)
        val response = mock.create(ApiService::class.java).getCategoryEntries(category)
        assertTrue(response.isSuccessful)
        val body = response.body()
        assertTrue(body is HatenaRss)
    }
}