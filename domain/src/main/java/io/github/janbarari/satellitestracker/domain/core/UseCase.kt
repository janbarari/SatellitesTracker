package io.github.janbarari.satellitestracker.domain.core

abstract class UseCase<I,O> {
    abstract suspend fun execute(input: I): O
}