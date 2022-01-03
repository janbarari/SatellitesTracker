package io.github.janbarari.satellitestracker.domain.repository

import io.github.janbarari.satellitestracker.domain.entity.Satellite
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    fun getAll(): Flow<List<Satellite>>
}