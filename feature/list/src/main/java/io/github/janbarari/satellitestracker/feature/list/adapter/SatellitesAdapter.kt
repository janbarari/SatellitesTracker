package io.github.janbarari.satellitestracker.feature.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.feature.list.R

class SatellitesAdapter(
    private var list: List<Satellite>,
    private val callback: (satellite: Satellite) -> Unit
) : RecyclerView.Adapter<SatelliteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        return SatelliteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.satellite_item_row,
                parent,
                false
            ),
            callback
        )
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun reset(newList: List<Satellite>) {
        updateList(newList)
    }

    fun updateList(newList: List<Satellite>) {
        val diffResult = DiffUtil.calculateDiff(
            AdapterDiffUtil(this.list, newList),
            true
        )
        diffResult.dispatchUpdatesTo(this)
        this.list = newList
    }

}