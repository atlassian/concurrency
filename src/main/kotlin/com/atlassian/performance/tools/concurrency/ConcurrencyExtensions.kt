package com.atlassian.performance.tools.concurrency

import org.apache.logging.log4j.Logger
import java.io.InterruptedIOException
import java.time.Duration
import java.time.Instant
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * Blocks until the [Future] returns, throws or [deadline] passes.
 */
fun <T> Future<T>.finishBy(
    deadline: Instant,
    logger: Logger
): T {
    val patience = maxOf(
        Duration.between(Instant.now(), deadline),
        Duration.ZERO
    )
    logger.debug("$this got $patience to finish")
    val result = get(patience.toMillis(), TimeUnit.MILLISECONDS)
    logger.debug("$this finished on time")
    return result
}

fun Throwable.representsInterrupt(): Boolean = when {
    this is InterruptedException || this is InterruptedIOException -> true
    else -> cause?.representsInterrupt() ?: false
}