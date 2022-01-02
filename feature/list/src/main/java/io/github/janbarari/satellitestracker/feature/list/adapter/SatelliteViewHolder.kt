package io.github.janbarari.satellitestracker.feature.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.janbarari.satellitestracker.core.extensions.whenClicked
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.feature.list.R
import io.github.janbarari.satellitestracker.feature.list.databinding.SatelliteItemRowBinding

class SatelliteViewHolder(
    private val view: View,
    private val callback: (satellite: Satellite) -> Unit
    ): RecyclerView.ViewHolder(view) {

    fun bind(satellite: Satellite) {
        val binding = SatelliteItemRowBinding.bind(view)

        with(binding) {

            title.text = satellite.name

            if (satellite.active) {
                statusImage.setImageResource(R.drawable.ic_active)
                description.text = "Active"
            } else {
                statusImage.setImageResource(R.drawable.ic_deactive)
                description.text = "Passive"
            }

            binding.root.whenClicked {
                callback(satellite)
            }

        }
    }

}