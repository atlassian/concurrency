package com.atlassian.performance.tools.concurrency.api

import java.util.concurrent.ScheduledExecutorService

class AbruptScheduledExecutorService(
    private val executorService: ScheduledExecutorService
) : AutoCloseable, ScheduledExecutorService by executorService {
    override fun close() {
        executorService.shutdownNow()
    }
}
