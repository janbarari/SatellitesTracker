package io.github.janbarari.satellitestracker.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Position(
    val posX: Double,
    val posY: Double
): Parcelable