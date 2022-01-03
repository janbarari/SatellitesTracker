package io.github.janbarari.satellitestracker.data.asset

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import javax.inject.Inject

class AssetFileProvider @Inject constructor(
    @ApplicationContext val context: Context,
    val moshi: Moshi
) {

    companion object {
        private const val SATELLITE_LIST_FILE_NAME = "satellite-list.json"
        private const val SATELLITE_DETAIL_FILE_NAME = "satellite-detail.json"
        private const val POSITIONS_FILE_NAME = "positions.json"
    }

    fun getSatelliteList(): List<Satellite>? {
        val listType = Types.newParameterizedType(List::class.java, Satellite::class.java)
        val adapter: JsonAdapter<List<Satellite>> = moshi.adapter(listType)
        val json = context.assets.open(SATELLITE_LIST_FILE_NAME).bufferedReader().use { it.readText() }
        return adapter.fromJson(json)
    }

}