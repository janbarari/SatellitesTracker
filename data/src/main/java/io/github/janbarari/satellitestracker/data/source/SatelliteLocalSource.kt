package io.github.janbarari.satellitestracker.data.source

import io.github.janbarari.satellitestracker.data.database.entity.Satellite

interface SatelliteLocalSource {
    suspend fun getAll(): List<Satellite>
    suspend fun getInitialData(): List<Satellite>
    suspend fun saveInLocal(data: List<Satellite>)
}