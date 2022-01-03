package io.github.janbarari.satellitestracker.domain.repository

import io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails
import kotlinx.coroutines.flow.Flow

interface SatelliteDetailsRepository {
    fun get(id: Int): Flow<SatelliteDetails?>
}