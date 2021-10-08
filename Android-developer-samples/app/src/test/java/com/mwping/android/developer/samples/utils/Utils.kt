package com.mwping.android.developer.samples.utils

import kotlinx.coroutines.delay

/**
 * weiping@atlasv.com
 * 2021/10/8
 */
object Utils {
    var startMs = 0L

    suspend fun doSomething(i: Int, sleepMs: Long, delayMs: Long) {
        println("${System.currentTimeMillis() - startMs}ms: Start[$i]doSomething on ${Thread.currentThread().name}")
        if (sleepMs > 0) {
            Thread.sleep(sleepMs)
        }
        if (delayMs > 0) {
            delay(delayMs)
        }
        println("${System.currentTimeMillis() - startMs}ms: End[$i]doSomething on ${Thread.currentThread().name}")
    }
}