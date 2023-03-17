package com.atlassian.performance.tools.concurrency.api

import org.apache.logging.log4j.LogManager
import org.junit.Test
import java.time.Duration
import java.time.Duration.ofMillis
import java.time.Duration.ofSeconds
import java.time.Instant.now
import java.util.concurrent.Executors.newCachedThreadPool

class GracefulTerminationKtTest {

    private val log = LogManager.getLogger(this::class.java)

    @Test
    fun shouldTerminateGracefully() {
        AbruptExecutorService(newCachedThreadPool()).use { executor ->
            executor
                .submit(Sleep(ofMillis(500)))
                .finishBy(now() + ofSeconds(4), log)
        }
    }

    private data class Sleep(
        private val duration: Duration
    ) : Runnable {
        override fun run() {
            Thread.sleep(duration.toMillis())
        }
    }
}