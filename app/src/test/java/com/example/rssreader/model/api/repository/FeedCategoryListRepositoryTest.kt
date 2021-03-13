package com.example.rssreader.model.api.repository

import com.example.rssreader.model.api.entity.FeedCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.jvm.Throws
import kotlin.test.Test
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class FeedCategoryListRepositoryTest {
    @Test
    @Throws(Throwable::class)
    fun `fetchCategoryList_success_feedCategoryをSuccessで返却`() = runBlockingTest {
        val repository = FeedCategoryListRepository(
            coroutineContext = Dispatchers.Unconfined
        )
        val result = repository.fetchCategoryList()
        assertTrue(result.isSuccess())
        assertTrue(result.getOrNull() is List<FeedCategory>)
    }
}