package io.github.janbarari.satellitestracker.data.source

import io.github.janbarari.satellitestracker.data.database.entity.SatelliteDetails

interface SatelliteDetailsLocalSource {
    suspend fun getAll(): List<SatelliteDetails>
    suspend fun getInitialData(): List<SatelliteDetails>
    suspend fun saveInLocal(data: List<SatelliteDetails>)
}