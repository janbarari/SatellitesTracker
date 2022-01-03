package io.github.janbarari.satellitestracker.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Position(
    val id: String,
    val positions: List<PositionXY>
): Parcelable

@Parcelize
data class PositionXY(
    val posX: Double,
    val posY: Double
): Parcelable