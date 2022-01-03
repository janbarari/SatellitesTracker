package io.github.janbarari.satellitestracker.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
data class SatelliteDetails(
    @PrimaryKey
    val id: Int,
    @Json(name = "cost_per_launch")
    val costPerLaunch: Int,
    @Json(name = "first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Int
) : Parcelable