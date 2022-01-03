package io.github.janbarari.satellitestracker.domain.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCaseNoInput<O>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(): Flow<O> {
        return execute().flowOn(dispatcher)
    }

    abstract suspend fun execute(): Flow<O>
}

abstract class FlowUseCase<I, O>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(input: I): Flow<O> {
        return execute(input).flowOn(dispatcher)
    }

    abstract suspend fun execute(input: I): Flow<O>
}