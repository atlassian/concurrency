package com.atlassian.performance.tools.concurrency.api

import java.util.concurrent.ExecutorService

/**
 * Shuts down "now" when closed.
 *
 * @since 1.1.0
 */
class AbruptExecutorService(
    private val executorService: ExecutorService
) : AutoCloseable, ExecutorService by executorService {
    override fun close() {
        executorService.shutdownNow()
    }
}