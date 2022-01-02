package io.github.janbarari.satellitestracker.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Satellite(
    val id: Int,
    val active: Boolean,
    val name: String
): Parcelable