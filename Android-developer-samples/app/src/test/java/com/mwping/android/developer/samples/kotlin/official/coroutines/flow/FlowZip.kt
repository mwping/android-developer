package com.mwping.android.developer.samples.kotlin.official.coroutines.flow

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * [flow zip](https://kotlinlang.org/docs/flow.html#zip)
 * weiping@atlasv.com
 * 2021/9/10
 */
class FlowZip {

    @InternalCoroutinesApi
    @Test
    fun main() = runBlocking {
        val startTime = System.currentTimeMillis()

        val nums = (1..3).asFlow().onEach {
            delay(3000)
            println("Int finish @ ${System.currentTimeMillis() - startTime}")
        }
        val strs = flowOf("one", "two", "three", "Four").onEach {
            delay(4000)
            println("String finish @ ${System.currentTimeMillis() - startTime}")
        }
        nums.zip(strs) { a, b -> "$a -> $b" }
            .collect {
                println(it + " @ ${System.currentTimeMillis() - startTime}\n")
            }
    }
}