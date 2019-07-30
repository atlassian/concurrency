package com.atlassian.performance.tools.concurrency.api

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class AbruptExecutorServiceTest {

    @Test
    fun shouldCloseHappily() {
        val thread = Executors.newSingleThreadExecutor()
        val abruptExecutor = AbruptExecutorService(thread)

        val answer = abruptExecutor.use { executor ->
            executor
                .submit(Callable { 42 })
                .get()
        }

        assertThat(answer, equalTo(42))
        assertThat(thread.isShutdown, equalTo(true))
    }

    @Test
    fun shouldCloseUnhappily() {
        val thread = Executors.newSingleThreadExecutor()
        val abruptExecutor = AbruptExecutorService(thread)

        try {
            abruptExecutor.use { executor ->
                executor.submit(Callable { 42 })
                throw Exception("something was wrong")
            }
        } catch (e: Exception) {
            assertThat(thread.isShutdown, equalTo(true))
        }
    }
}