package io.github.janbarari.satellitestracker.data.mapper

import io.github.janbarari.satellitestracker.data.database.entity.Position
import io.github.janbarari.satellitestracker.domain.core.Mapper
import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import javax.inject.Inject

class PositionMapper: Mapper<Position, io.github.janbarari.satellitestracker.domain.entity.Position>() {
    override fun map(left: Position): io.github.janbarari.satellitestracker.domain.entity.Position {
        val posXYs = left.positions.map { PositionXY(it.posX, it.posY) }
        return io.github.janbarari.satellitestracker.domain.entity.Position(
            left.id,
            posXYs
        )
    }
}