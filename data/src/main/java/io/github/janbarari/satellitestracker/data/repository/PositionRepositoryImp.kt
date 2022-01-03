package io.github.janbarari.satellitestracker.data.repository

import io.github.janbarari.satellitestracker.data.mapper.PositionMapper
import io.github.janbarari.satellitestracker.data.source.PositionLocalSource
import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import io.github.janbarari.satellitestracker.domain.repository.PositionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PositionRepositoryImp @Inject constructor(
    private val source: PositionLocalSource,
    private val positionMapper: PositionMapper
) : PositionRepository {

    override fun get(id: String): Flow<List<PositionXY>?> {
        return flow {

            val dbData = source.getAll()

            if (dbData.isNotEmpty()) {

                val details = dbData.find { it.id == id }

                if (details == null) {
                    emit(null)
                    return@flow
                }

                emit(positionMapper.map(details).positions)
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

                emit(positionMapper.map(details).positions)
                return@flow
            }

            emit(null)

        }
    }

}