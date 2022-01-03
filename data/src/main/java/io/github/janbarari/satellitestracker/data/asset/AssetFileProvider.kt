package io.github.janbarari.satellitestracker.data.asset

import io.github.janbarari.satellitestracker.data.database.entity.Position
import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import io.github.janbarari.satellitestracker.data.database.entity.SatelliteDetails

interface AssetFileProvider {
    fun getSatelliteList(): List<Satellite>
    fun getSatelliteDetails(): List<SatelliteDetails>
    fun getPositions(): List<Position>
}