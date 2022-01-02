package io.gthub.janbarari.satellitestracker.feature.details

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import io.gthub.janbarari.satellitestracker.core.ViewModelFragment
import io.gthub.janbarari.satellitestracker.feature.details.databinding.SatellitesDetailsFragmentBinding

@AndroidEntryPoint
class SatellitesDetailsFragment: ViewModelFragment<SatellitesDetailsFragmentBinding, SatellitesDetailsViewModel>(
    R.layout.satellites_details_fragment,
    SatellitesDetailsViewModel::class.java
) {
    override fun getBindingLayout(v: View) = SatellitesDetailsFragmentBinding.bind(v)



}