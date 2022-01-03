package io.github.janbarari.satellitestracker.data.source.imp

import io.github.janbarari.satellitestracker.data.asset.AssetFileProvider
import io.github.janbarari.satellitestracker.data.database.dao.SatelliteDao
import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import io.github.janbarari.satellitestracker.data.source.SatelliteLocalSource
import javax.inject.Inject

class SatelliteLocalSourceImp @Inject constructor(
    private val satelliteDAO: SatelliteDao,
    private val assetFileProvider: AssetFileProvider
) : SatelliteLocalSource {

    override suspend fun getAll(): List<Satellite> {
        return satelliteDAO.getAll()
    }

    override suspend fun getInitialData(): List<Satellite> {
        val result = arrayListOf<Satellite>()
        val initialSatellitesList = assetFileProvider.getSatelliteList()
        if (initialSatellitesList.isNullOrEmpty()) {
            return result
        }
        initialSatellitesList.forEach {
            result.add(it)
        }
        return result
    }

    override suspend fun saveInLocal(data: List<Satellite>) {
        data.forEach {
            satelliteDAO.insert(it)
        }
    }

}