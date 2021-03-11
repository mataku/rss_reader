package com.example.rssreader.model.api.repository

import com.example.rssreader.model.api.RSSClient
import com.example.rssreader.model.api.entity.HatenaRss
import com.example.rssreader.model.api.entity.HatenaRssItem
import com.example.rssreader.model.api.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import retrofit2.Response
import kotlin.jvm.Throws
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class FeedRepositoryTest {

    @Mock
    private lateinit var rssClient: RSSClient

    @Mock
    private lateinit var apiService: ApiService

    private val mockHatenaRssItem = HatenaRssItem(title = "", link = "https://example.com", imageUrl = "https://example.com/image.jpg")

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(rssClient.create(ApiService::class.java)).thenReturn(apiService)
    }

    @Test
    @Throws(Throwable::class)
    fun `fetch_categoryIsNull_総合のrssを取得成功_HatenaRssItemのリストをsuccessで返す`() = runBlockingTest {
        Mockito.`when`(apiService.getOverallEntries()).thenReturn(
            Response.success(200, HatenaRss(mutableListOf(mockHatenaRssItem)))
        )

        val repository = FeedRepository(
            coroutineContext = Dispatchers.Unconfined,
            rssClient = rssClient
        )

        val result = repository.fetch(null)
        assertTrue(result.isSuccess())
        assertTrue(result.getOrNull() is List<HatenaRssItem>)
    }
}
