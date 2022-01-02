package io.github.janbarari.satellitestracker.feature.list

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.satellitestracker.core.ViewModelFragment
import io.github.janbarari.satellitestracker.feature.list.databinding.SatellitesListFragmentBinding

@AndroidEntryPoint
class SatellitesListFragment: ViewModelFragment<SatellitesListFragmentBinding, SatellitesListViewModel>(
    R.layout.satellites_list_fragment,
    SatellitesListViewModel::class.java
) {
    override fun getBindingLayout(v: View) = SatellitesListFragmentBinding.bind(v)


}