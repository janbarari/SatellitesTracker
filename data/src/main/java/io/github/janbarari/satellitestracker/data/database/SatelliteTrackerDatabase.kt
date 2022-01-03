package io.github.janbarari.satellitestracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.janbarari.satellitestracker.data.database.SatelliteTrackerDatabase.Companion.VERSION
import io.github.janbarari.satellitestracker.data.database.dao.PositionDao
import io.github.janbarari.satellitestracker.data.database.dao.SatelliteDao
import io.github.janbarari.satellitestracker.data.database.dao.SatelliteDetailsDao
import io.github.janbarari.satellitestracker.data.database.entity.Position
import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import io.github.janbarari.satellitestracker.data.database.entity.SatelliteDetails

@Database(
    entities = [
        Position::class,
        Satellite::class,
        SatelliteDetails::class
    ],
    version = VERSION,
    exportSchema = false
)
abstract class SatelliteTrackerDatabase : RoomDatabase() {

    abstract fun getPositionDAO(): PositionDao
    abstract fun getSatelliteDAO(): SatelliteDao
    abstract fun getSatelliteDetailsDAO(): SatelliteDetailsDao

    companion object {
        private const val DATABASE_NAME = "satellitetracker"
        const val VERSION = 1

        operator fun invoke(context: Context): SatelliteTrackerDatabase {
            return Room
                .databaseBuilder(context, SatelliteTrackerDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}