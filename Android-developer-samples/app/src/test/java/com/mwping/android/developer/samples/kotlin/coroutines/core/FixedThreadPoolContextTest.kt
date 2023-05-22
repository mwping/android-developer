package com.mwping.android.developer.samples.kotlin.coroutines.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * weiping@atlasv.com
 * 2021/10/8
 */
class FixedThreadPoolContextTest {
    @Test
    fun main() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(cacheThreadPoolDispatcher) {
                val start = System.currentTimeMillis()
                val deferreds = (1..20).map {
                    async { doSomethingUseful(it) }
                }
                val sum = deferreds.awaitAll().sum()
                println("The answer is $sum")
                println("Completed in ${System.currentTimeMillis() - start} ms")
            }
        }
        Thread.sleep(15_000)
    }

    suspend fun doSomethingUseful(index: Int): Int {
        println("Start doSomethingUseful: $index(${Thread.currentThread().name})")
        Thread.sleep(1000L)
        println("End doSomethingUseful: $index")
        return index
    }

    companion object {
        val cacheThreadPoolDispatcher by lazy {
            ThreadPoolExecutor(
                3,
                3,
                60,
                TimeUnit.SECONDS,
                LinkedBlockingQueue()
            ).asCoroutineDispatcher()
        }
    }

}