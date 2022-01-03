package io.github.janbarari.satellitestracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import kotlinx.coroutines.flow.Flow

@Dao
interface SatelliteDao {

    @Query("select * from satellite")
    suspend fun getAll(): List<Satellite>

    @Query("delete from satellite")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(satellite: Satellite)

}