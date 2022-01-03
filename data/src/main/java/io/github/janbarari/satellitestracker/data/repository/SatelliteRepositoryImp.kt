package io.github.janbarari.satellitestracker.data.repository

import io.github.janbarari.satellitestracker.data.mapper.SatelliteListMapper
import io.github.janbarari.satellitestracker.data.source.SatelliteLocalSource
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteRepositoryImp @Inject constructor(
    private val source: SatelliteLocalSource,
    private val satelliteListMapper: SatelliteListMapper
) : SatelliteRepository {

    override fun getAll(): Flow<List<Satellite>> {
        return flow {
            val localSatellites = source.getAll()
            if (localSatellites.isEmpty()) {
                val initialSatellites = source.getInitialData()
                if (initialSatellites.isNullOrEmpty().not()) {
                    source.saveInLocal(initialSatellites)
                    emit(
                        satelliteListMapper.map(initialSatellites)
                    )
                }
            } else {
                emit(satelliteListMapper.map(localSatellites))
            }
        }
    }

}