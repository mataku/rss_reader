package com.example.rssreader.test_helper

import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class TestObserver<T>(count: Int = 1) : Observer<T> {

    private val values = mutableListOf<T?>()
    private val latch = CountDownLatch(count)

    override fun onChanged(t: T) {
        values.add(t)
        latch.countDown()
    }

    fun get(): T? {
        if (values.isEmpty()) {
            throw IllegalStateException("onChanged is not called")
        }
        return values[values.size - 1]
    }

    fun await(timeout: Long = 1, unit: TimeUnit = TimeUnit.SECONDS) {
        if (!latch.await(timeout, unit)) {
            throw TimeoutException()
        }
    }
}