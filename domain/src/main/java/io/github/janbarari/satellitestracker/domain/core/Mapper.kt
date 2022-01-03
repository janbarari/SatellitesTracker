package io.github.janbarari.satellitestracker.domain.core

abstract class Mapper<L, R> {
    abstract fun map(left: L): R
}