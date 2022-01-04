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

            //If cache is not empty then get positions form cache
            val dbData = source.getAll()
            if (dbData.isNotEmpty()) {
                emit(satelliteListMapper.map(dbData))
                return@flow
            }

            //If cache is empty then get initial data from json file and add in cache
            val initialSatellites = source.getInitialData()
            if (initialSatellites.isNotEmpty()) {
                source.saveInLocal(initialSatellites)
                emit(satelliteListMapper.map(initialSatellites))
                return@flow
            }

            emit(listOf())
        }
    }

}