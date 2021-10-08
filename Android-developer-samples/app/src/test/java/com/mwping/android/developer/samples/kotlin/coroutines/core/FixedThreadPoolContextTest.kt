package com.mwping.android.developer.samples.kotlin.coroutines.core

import com.mwping.android.developer.samples.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.junit.Test
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * weiping@atlasv.com
 * 2021/10/8
 */
class FixedThreadPoolContextTest {
    @Test
    fun main() {
        Utils.startMs = System.currentTimeMillis()
        GlobalScope.launch(cacheThreadPoolDispatcher) {
            Utils.doSomething(1, sleepMs = 0, delayMs = 0)
            Utils.doSomething(2, sleepMs = 6_000, delayMs = 0)
            Utils.doSomething(3, sleepMs = 0, delayMs = 6_000)
        }
        Thread.sleep(30_000)
    }

    companion object {
        val cacheThreadPoolDispatcher by lazy {
            ThreadPoolExecutor(
                0,
                1,
                5,
                TimeUnit.SECONDS,
                SynchronousQueue()
            ).asCoroutineDispatcher()
        }
    }

}