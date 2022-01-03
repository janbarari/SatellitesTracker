package io.github.janbarari.satellitestracker.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
data class Position(
    val id: String,
    val positions: List<PositionXY>
) : Parcelable {

    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0

}

@Parcelize
@JsonClass(generateAdapter = true)
data class PositionXY(
    val posX: Double,
    val posY: Double
): Parcelable