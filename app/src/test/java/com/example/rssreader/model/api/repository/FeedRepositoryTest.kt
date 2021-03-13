package com.example.rssreader.model.api.repository

import com.example.rssreader.model.api.RSSClient
import com.example.rssreader.model.api.entity.HatenaRss
import com.example.rssreader.model.api.entity.HatenaRssItem
import com.example.rssreader.model.api.entity.presentation.NetworkError
import com.example.rssreader.model.api.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import retrofit2.Response
import java.util.concurrent.TimeoutException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class FeedRepositoryTest {

    @Mock
    private lateinit var rssClient: RSSClient

    @Mock
    private lateinit var apiService: ApiService

    private val mockHatenaRssItem = HatenaRssItem(
        title = "",
        link = "https://example.com",
        imageUrl = "https://example.com/image.jpg"
    )

    private val category = "social"

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

        val result = repository.fetch("")
        assertTrue(result.isSuccess())
        assertTrue(result.getOrNull() is List<HatenaRssItem>)
    }

    @Test
    @Throws(Throwable::class)
    fun `fetch_categoryIsNull_総合のrssを取得失敗_NetworkErrorで返す`() = runBlockingTest {
        Mockito.`when`(apiService.getOverallEntries()).thenReturn(
            Response.error(
                404,
                ResponseBody.Companion.create("application/xml".toMediaTypeOrNull(), "404")
            )
        )

        val repository = FeedRepository(
            coroutineContext = Dispatchers.Unconfined,
            rssClient = rssClient
        )

        val result = repository.fetch("")
        assertTrue(result.isFailure())
        assertTrue(result.exceptionOrNull() is NetworkError)
    }

    @Test
    @Throws(Throwable::class)
    fun `fetch_categoryが渡される_rssを取得成功_HatenaRssItemのリストをsuccessで返す`() = runBlockingTest {
        Mockito.`when`(apiService.getCategoryEntries(category)).thenReturn(
            Response.success(200, HatenaRss(mutableListOf(mockHatenaRssItem)))
        )

        val repository = FeedRepository(
            coroutineContext = Dispatchers.Unconfined,
            rssClient = rssClient
        )

        val result = repository.fetch(category)
        assertTrue(result.isSuccess())
        assertTrue(result.getOrNull() is List<HatenaRssItem>)
    }

    @Test
    @Throws(Throwable::class)
    fun `fetch_categoryが渡される_rssを取得失敗_NetworkErrorで返す`() = runBlockingTest {
        Mockito.`when`(apiService.getCategoryEntries(category)).thenReturn(
            Response.error(
                404,
                ResponseBody.Companion.create("application/xml".toMediaTypeOrNull(), "404")
            )
        )

        val repository = FeedRepository(
            coroutineContext = Dispatchers.Unconfined,
            rssClient = rssClient
        )

        val result = repository.fetch(category)
        assertTrue(result.isFailure())
        assertTrue(result.exceptionOrNull() is NetworkError)
    }

    @Test
    @Throws(Throwable::class)
    fun `fetch_throwError_発生したエラーをfailureで返す`() = runBlockingTest {
        Mockito.`when`(apiService.getCategoryEntries(category)).then {
            throw TimeoutException("error")
        }

        val repository = FeedRepository(
            coroutineContext = Dispatchers.Unconfined,
            rssClient = rssClient
        )

        val result = repository.fetch(category)
        assertTrue(result.isFailure())
        assertTrue(result.exceptionOrNull() is TimeoutException)
    }
}