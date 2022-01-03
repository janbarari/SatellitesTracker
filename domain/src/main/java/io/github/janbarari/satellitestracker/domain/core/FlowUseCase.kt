package io.github.janbarari.satellitestracker.domain.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCaseNoInput<O>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun <T> invoke(): Flow<O> {
        return execute().flowOn(dispatcher)
    }

    abstract suspend fun execute(): Flow<O>
}