package io.github.janbarari.satellitestracker.data.repository

import io.github.janbarari.satellitestracker.data.mapper.SatelliteDetailsMapper
import io.github.janbarari.satellitestracker.data.source.SatelliteDetailsLocalSource
import io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails
import io.github.janbarari.satellitestracker.domain.repository.SatelliteDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteDetailsRepositoryImp @Inject constructor(
    private val source: SatelliteDetailsLocalSource,
    private val satelliteListMapper: SatelliteDetailsMapper
) : SatelliteDetailsRepository {

    override fun get(id: Int): Flow<SatelliteDetails?> {
        return flow {

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