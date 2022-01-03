package io.github.janbarari.satellitestracker.data.asset

import com.squareup.moshi.JsonClass
import io.github.janbarari.satellitestracker.data.database.entity.Position

@JsonClass(generateAdapter = true)
data class PositionParserClass(
    val list: List<Position>
)