package com.atlassian.performance.tools.concurrency.api

import java.util.concurrent.Callable

/**
 * Offers the stack trace for inspection by any thread.
 */
class TraceableTask<T>(
    private val task: () -> T
) : Callable<T> {

    private lateinit var thread: Thread

    override fun call(): T {
        thread = Thread.currentThread()
        return task.invoke()
    }

    /**
     * @return Holds the current stack trace of the thread running the [task].
     */
    fun trace(): Exception {
        val trace = Exception("Task trace")
        trace.stackTrace = thread.stackTrace
        return trace
    }
}