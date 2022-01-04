package io.github.janbarari.satellitestracker.data.source.imp

import io.github.janbarari.satellitestracker.data.asset.AssetFileProvider
import io.github.janbarari.satellitestracker.data.database.dao.SatelliteDetailsDao
import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import io.github.janbarari.satellitestracker.data.database.entity.SatelliteDetails
import io.github.janbarari.satellitestracker.data.source.SatelliteDetailsLocalSource
import javax.inject.Inject

class SatelliteDetailsLocalSourceImp(
    private val satelliteDetailsDAO: SatelliteDetailsDao,
    private val assetFileProvider: AssetFileProvider
) : SatelliteDetailsLocalSource {

    override suspend fun getAll(): List<SatelliteDetails> {
        return satelliteDetailsDAO.getAll()
    }

    override suspend fun getInitialData(): List<SatelliteDetails> {
        val result = arrayListOf<SatelliteDetails>()
        val initialSatellitesList = assetFileProvider.getSatelliteDetails()
        if (initialSatellitesList.isNullOrEmpty()) {
            return result
        }
        initialSatellitesList.forEach {
            result.add(it)
        }
        return result
    }

    override suspend fun saveInLocal(data: List<SatelliteDetails>) {
        data.forEach {
            satelliteDetailsDAO.insert(it)
        }
    }

}