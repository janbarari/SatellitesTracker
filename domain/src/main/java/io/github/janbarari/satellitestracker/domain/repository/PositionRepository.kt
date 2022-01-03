package io.github.janbarari.satellitestracker.domain.repository

import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import kotlinx.coroutines.flow.Flow

interface PositionRepository {
    fun get(id: String): Flow<List<PositionXY>?>
}