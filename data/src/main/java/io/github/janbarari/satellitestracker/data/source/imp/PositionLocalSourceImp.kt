package io.github.janbarari.satellitestracker.data.source.imp

import io.github.janbarari.satellitestracker.data.asset.AssetFileProvider
import io.github.janbarari.satellitestracker.data.database.dao.PositionDao
import io.github.janbarari.satellitestracker.data.database.entity.Position
import io.github.janbarari.satellitestracker.data.source.PositionLocalSource
import javax.inject.Inject

class PositionLocalSourceImp(
    private val positionDao: PositionDao,
    private val assetFileProvider: AssetFileProvider
) : PositionLocalSource {

    override suspend fun getAll(): List<Position> {
        return positionDao.getAll()
    }

    override suspend fun getInitialData(): List<Position> {
        val result = arrayListOf<Position>()
        val initialSatellitesList = assetFileProvider.getPositions()
        if (initialSatellitesList.isNullOrEmpty()) {
            return result
        }
        initialSatellitesList.forEach {
            result.add(it)
        }
        return result
    }

    override suspend fun saveInLocal(data: List<Position>) {
        data.forEach {
            positionDao.insert(it)
        }
    }

}