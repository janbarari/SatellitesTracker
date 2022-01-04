package io.github.janbarari.satellitestracker.data.mapper

import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import io.github.janbarari.satellitestracker.domain.core.Mapper
import javax.inject.Inject

class SatelliteListMapper: Mapper<List<Satellite>, List<io.github.janbarari.satellitestracker.domain.entity.Satellite>>() {
    override fun map(left: List<Satellite>): List<io.github.janbarari.satellitestracker.domain.entity.Satellite> {
        return left
            .map {
                io.github.janbarari.satellitestracker.domain.entity.Satellite(
                    it.id,
                    it.active,
                    it.name
                )
            }
    }
}