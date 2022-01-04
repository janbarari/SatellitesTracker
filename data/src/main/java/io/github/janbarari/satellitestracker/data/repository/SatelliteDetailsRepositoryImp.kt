package io.github.janbarari.satellitestracker.data.repository

import io.github.janbarari.satellitestracker.data.mapper.SatelliteDetailsMapper
import io.github.janbarari.satellitestracker.data.source.SatelliteDetailsLocalSource
import io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails
import io.github.janbarari.satellitestracker.domain.repository.SatelliteDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteDetailsRepositoryImp(
    private val source: SatelliteDetailsLocalSource,
    private val satelliteListMapper: SatelliteDetailsMapper
) : SatelliteDetailsRepository {

    override fun get(id: Int): Flow<SatelliteDetails?> {
        return flow {

            //If cache is not empty then get positions form cache

            val dbData = source.getAll()
            if (dbData.isNotEmpty()) {
                val details = dbData.find { it.id == id }
                if (details == null) {
                    emit(null)
                    return@flow
                }
                emit(satelliteListMapper.map(details))
                return@flow
            }

            //If cache is empty then get initial data from json file and add in cache
            val initialSatellites = source.getInitialData()
            if (initialSatellites.isNotEmpty()) {
                source.saveInLocal(initialSatellites)
                val details = initialSatellites.find { it.id == id }
                if (details == null) {
                    emit(null)
                    return@flow
                }
                emit(satelliteListMapper.map(details))
                return@flow
            }

            emit(null)

        }
    }

}