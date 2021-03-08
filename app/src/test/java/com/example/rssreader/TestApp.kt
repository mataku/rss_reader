package com.example.rssreader

import android.app.Application
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method
import kotlin.test.fail

class TestApp : Application(), TestLifecycleApplication {
    override fun beforeTest(method: Method?) {
        Thread.currentThread().setUncaughtExceptionHandler { _, _ -> fail() }
    }

    override fun afterTest(method: Method?) {

    }

    override fun prepareTest(test: Any?) {
        
    }
}