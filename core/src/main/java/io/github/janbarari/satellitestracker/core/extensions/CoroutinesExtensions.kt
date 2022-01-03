package io.github.janbarari.satellitestracker.core.extensions

import kotlinx.coroutines.*

fun repeatJob(timeInterval: Long, block: ()->Unit): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        while (isActive) {
            block()
            delay(timeInterval)
        }
    }
}