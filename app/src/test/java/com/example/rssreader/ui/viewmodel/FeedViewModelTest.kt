package com.example.rssreader.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rssreader.model.api.entity.HatenaRssItem
import com.example.rssreader.model.api.entity.presentation.NetworkError
import com.example.rssreader.model.api.entity.presentation.RequestResult
import com.example.rssreader.model.api.repository.FeedRepository
import com.example.rssreader.test_helper.TestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class FeedViewModelTest {

    // Mock mainLooper thread
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var feedRepository: FeedRepository

    private val category = ""

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @Throws(Throwable::class)
    fun fetchFeed_success_returnFeedUiModelWithRssItemList() = runBlocking {
        val mockItem = HatenaRssItem(
            title = "動物癒し特集",
            link = "https://example.com",
            imageUrl = "https://example.com/image.jpg"
        )


        Mockito.`when`(feedRepository.fetch(category)).thenReturn(
            RequestResult.success(
                listOf(
                    mockItem
                )
            )
        )
        val observer = TestObserver<FeedViewModel.FeedUiModel>()
        val viewModel = FeedViewModel(feedRepository = feedRepository)
        viewModel.feedLiveData.observeForever(observer)

        viewModel.fetchFeed(category)
        observer.await()

        val result = observer.get()
        assertNotNull(result)
        assertNull(result.error)
        assertTrue(result.rssItemList.isNotEmpty())
    }

    @Test
    @Throws(Throwable::class)
    fun fetchFeed_failure_returnFeedUiModelWithError() = runBlocking {
        Mockito.`when`(feedRepository.fetch(category)).thenReturn(
            RequestResult.failure(
                NetworkError(responseCode = 404)
            )
        )
        val observer = TestObserver<FeedViewModel.FeedUiModel>()
        val viewModel = FeedViewModel(feedRepository = feedRepository)
        viewModel.feedLiveData.observeForever(observer)

        viewModel.fetchFeed(category)
        observer.await()

        val result = observer.get()
        assertNotNull(result)
        assertTrue(result.error is NetworkError)
        assertTrue(result.rssItemList.isEmpty())
    }

}