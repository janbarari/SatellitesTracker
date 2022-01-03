package io.github.janbarari.satellitestracker.data.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.github.janbarari.satellitestracker.data.database.entity.PositionXY
import io.github.janbarari.satellitestracker.data.database.entity.Satellite

class TypeConverters {

    private var moshi: Moshi = Moshi.Builder().build()
    private val typeListPositionXY = Types.newParameterizedType(List::class.java, PositionXY::class.java)
    private val adapterListPositionXY: JsonAdapter<List<PositionXY>> = moshi.adapter(typeListPositionXY)

    @TypeConverter
    fun fromListPositionXY(value: List<PositionXY>): String? {
        return adapterListPositionXY.toJson(value)
    }

    @TypeConverter
    fun toListPositionXY(value: String): List<PositionXY> {
        return adapterListPositionXY.fromJson(value) ?: listOf()
    }

}