package com.atlassian.performance.tools.concurrency

import java.util.concurrent.Callable

class TraceableTask<T>(
    private val task: () -> T
) : Callable<T> {

    private lateinit var thread: Thread

    override fun call(): T {
        thread = Thread.currentThread()
        return task.invoke()
    }

    fun trace(): Exception {
        val trace = Exception("Task trace")
        trace.stackTrace = thread.stackTrace
        return trace
    }
}