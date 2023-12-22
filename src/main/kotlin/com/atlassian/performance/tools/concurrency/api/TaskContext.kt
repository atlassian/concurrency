package com.atlassian.performance.tools.concurrency.api

import com.atlassian.performance.tools.jvmtasks.api.TaskScope
import org.apache.logging.log4j.CloseableThreadContext
import org.apache.logging.log4j.ThreadContext
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.function.Supplier

/**
 * Embeds the [label]led [task] within the current log context.
 */
fun <T> ExecutorService.submitWithLogContext(
    label: String,
    task: () -> T
): CompletableFuture<T> {
    val logStack = ThreadContext.getImmutableStack()
    val logContext = ThreadContext.getImmutableContext()

    return CompletableFuture.supplyAsync(
        Supplier {
            CloseableThreadContext.pushAll(logStack.asList())
                .putAll(logContext)
                .use { TaskScope.task(label, Callable(task)) }
        },
        this
    )
}