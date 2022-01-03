package io.github.janbarari.satellitestracker.domain.core

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<I, O> {
    abstract suspend fun execute(input: I): Flow<O>
}

abstract class FlowUseCaseNoInput<O> {
    abstract suspend fun execute(): Flow<O>
}