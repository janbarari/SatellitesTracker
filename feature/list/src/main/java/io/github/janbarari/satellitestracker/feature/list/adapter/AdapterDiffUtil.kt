package io.github.janbarari.satellitestracker.feature.list.adapter

import androidx.recyclerview.widget.DiffUtil
import io.github.janbarari.satellitestracker.domain.entity.Satellite

class AdapterDiffUtil(
    private val oldSatellites: List<Satellite>,
    private val newSatellites: List<Satellite>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldSatellites.size
    }

    override fun getNewListSize(): Int {
        return newSatellites.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldSatellites[oldItemPosition].id == newSatellites[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldSatellites[oldItemPosition] == newSatellites[newItemPosition]
    }
}