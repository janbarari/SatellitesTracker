package io.github.janbarari.satellitestracker.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
data class Satellite(
    @PrimaryKey
    val id: Int,
    val active: Boolean,
    val name: String
) : Parcelable