package com.atlassian.performance.tools.concurrency.api

import com.atlassian.performance.tools.concurrency.api.TraceableTask
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class TraceableTaskTest {

    @Test
    fun shouldTraceTimedOutFuture() {
        val pool = Executors.newSingleThreadExecutor()
        val task = TraceableTask { spin() }
        val future = pool.submit(task)
        lateinit var trace: Exception

        try {
            future.get(50, TimeUnit.MILLISECONDS)
        } catch (e: Exception) {
            trace = task.trace()
        }

        pool.shutdownNow()
        assertThat(trace.stackTrace[1].methodName, equalTo("spin"))
    }

    private fun spin() {
        while (true) {
            Thread.sleep(10)
        }
    }
}