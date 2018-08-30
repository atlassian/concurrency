package com.atlassian.performance.tools.concurrency.api

import org.apache.logging.log4j.Logger
import java.time.Instant
import java.util.concurrent.Future

/**
 * Shows what the [task] is doing when [future] fails to terminate gracefully.
 */
class TraceableFuture<T>(
    private val task: TraceableTask<T>,
    private val future: Future<T>
) {
    fun finishBy(
        deadline: Instant,
        logger: Logger
    ): T {
        try {
            return future.finishBy(deadline, logger)
        } catch (e: Exception) {
            e.addSuppressed(task.trace())
            throw Exception("$future failed to finish by $deadline", e)
        }
    }
}