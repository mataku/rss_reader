package com.example.rssreader.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rssreader.model.api.entity.FeedCategory
import com.example.rssreader.model.api.entity.presentation.NetworkError
import com.example.rssreader.model.api.entity.presentation.RequestResult
import com.example.rssreader.model.api.repository.FeedCategoryListRepository
import com.example.rssreader.test_helper.TestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
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

class MainViewModelTest {
    // Mock mainLooper thread
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var feedCategoryListRepository: FeedCategoryListRepository

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
    fun fetchCategoryList_success_returnsUiModelWithCategoryList() = runBlockingTest {
        Mockito.`when`(feedCategoryListRepository.fetchCategoryList()).thenReturn(
            RequestResult.success(listOf(FeedCategory(path = "", localizedName = "総合")))
        )

        val viewModel = MainViewModel(categoryListRepository = feedCategoryListRepository)
        val observer = TestObserver<MainViewModel.MainUiModel>()
        viewModel.categoryLiveData.observeForever(observer)

        viewModel.fetchCategoryList()
        observer.await()

        val result = observer.get()
        assertNotNull(result)
        assertTrue(result.categoryList.isNotEmpty())
        assertNull(result.throwable)
    }

    @Test
    @Throws(Throwable::class)
    fun fetchCategoryList_failure_returnsUiModelWithError() = runBlockingTest {
        Mockito.`when`(feedCategoryListRepository.fetchCategoryList()).thenReturn(
            RequestResult.failure(NetworkError(responseCode = 422))
        )

        val viewModel = MainViewModel(categoryListRepository = feedCategoryListRepository)
        val observer = TestObserver<MainViewModel.MainUiModel>()
        viewModel.categoryLiveData.observeForever(observer)

        viewModel.fetchCategoryList()
        observer.await()

        val result = observer.get()
        assertNotNull(result)
        assertTrue(result.categoryList.isEmpty())
        assertTrue(result.throwable is NetworkError)
    }
}