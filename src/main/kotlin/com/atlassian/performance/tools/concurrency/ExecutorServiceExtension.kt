package com.atlassian.performance.tools.concurrency

import com.atlassian.performance.tools.jvmtasks.api.TaskTimer
import org.apache.logging.log4j.CloseableThreadContext
import org.apache.logging.log4j.ThreadContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.function.Supplier

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
                .use { TaskTimer.time(label, task) }
        },
        this
    )
}