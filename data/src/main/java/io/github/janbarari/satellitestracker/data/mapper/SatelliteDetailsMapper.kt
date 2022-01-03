package io.github.janbarari.satellitestracker.data.mapper

import io.github.janbarari.satellitestracker.data.database.entity.SatelliteDetails
import io.github.janbarari.satellitestracker.domain.core.Mapper
import javax.inject.Inject

class SatelliteDetailsMapper @Inject constructor() :
    Mapper<SatelliteDetails, io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails>() {
    override fun map(left: SatelliteDetails): io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails {
        return io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails(
            left.id,
            left.costPerLaunch,
            left.firstFlight,
            left.height,
            left.mass
        )
    }

}