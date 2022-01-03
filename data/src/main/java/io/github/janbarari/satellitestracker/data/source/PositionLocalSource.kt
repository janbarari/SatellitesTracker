package io.github.janbarari.satellitestracker.data.source

import io.github.janbarari.satellitestracker.data.database.entity.Position

interface PositionLocalSource {
    suspend fun getAll(): List<Position>
    suspend fun getInitialData(): List<Position>
    suspend fun saveInLocal(data: List<Position>)
}