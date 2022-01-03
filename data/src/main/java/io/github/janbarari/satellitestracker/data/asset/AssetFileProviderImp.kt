package io.github.janbarari.satellitestracker.data.asset

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.janbarari.satellitestracker.data.database.entity.Position
import io.github.janbarari.satellitestracker.data.database.entity.Satellite
import io.github.janbarari.satellitestracker.data.database.entity.SatelliteDetails
import javax.inject.Inject

class AssetFileProviderImp @Inject constructor(
    @ApplicationContext val context: Context,
    private val moshi: Moshi
) : AssetFileProvider {

    companion object {
        private const val SATELLITE_LIST_FILE_NAME = "satellite-list.json"
        private const val SATELLITE_DETAIL_FILE_NAME = "satellite-detail.json"
        private const val POSITIONS_FILE_NAME = "positions.json"
    }

    override fun getSatelliteList(): List<Satellite> {
        val listType = Types.newParameterizedType(List::class.java, Satellite::class.java)
        val adapter: JsonAdapter<List<Satellite>> = moshi.adapter(listType)
        val json = context.assets.open(SATELLITE_LIST_FILE_NAME).bufferedReader().use { it.readText() }
        return adapter.fromJson(json) ?: listOf()
    }

    override fun getSatelliteDetails(): List<SatelliteDetails> {
        val listType = Types.newParameterizedType(List::class.java, SatelliteDetails::class.java)
        val adapter: JsonAdapter<List<SatelliteDetails>> = moshi.adapter(listType)
        val json = context.assets.open(SATELLITE_DETAIL_FILE_NAME).bufferedReader().use { it.readText() }
        return adapter.fromJson(json) ?: listOf()
    }

    override fun getPositions(): List<Position> {
        val adapter: JsonAdapter<PositionParserClass> = moshi.adapter(PositionParserClass::class.java)
        val json = context.assets.open(POSITIONS_FILE_NAME).bufferedReader().use { it.readText() }
        val result = adapter.fromJson(json)
        return result?.list ?: listOf()
    }

}