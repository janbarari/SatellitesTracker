package io.github.janbarari.satellitestracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.janbarari.satellitestracker.data.database.entity.SatelliteDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface SatelliteDetailsDao {

    @Query("select * from satellitedetails")
    suspend fun getAll(): List<SatelliteDetails>

    @Query("delete from satellite")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(satelliteDetails: SatelliteDetails)

}