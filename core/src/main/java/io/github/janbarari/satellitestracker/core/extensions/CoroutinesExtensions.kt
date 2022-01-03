package io.github.janbarari.satellitestracker.core.extensions

import kotlinx.coroutines.*

fun repeatJob(timeInterval: Long, block: ()->Unit): Job {
    return CoroutineScope(Dispatchers.Default).launch {
        while (isActive) {
            block()
            delay(timeInterval)
        }
    }
}