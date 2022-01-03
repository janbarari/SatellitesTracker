package io.github.janbarari.satellitestracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.janbarari.satellitestracker.data.database.entity.Position
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionDao {

    @Query("select * from position")
    suspend fun getAll(): List<Position>

    @Query("delete from position")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(position: Position)

}