package io.github.janbarari.satellitestracker.feature.details

import android.view.View
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.satellitestracker.core.extensions.arg
import io.github.janbarari.satellitestracker.core.fragment.ViewModelFragment
import io.github.janbarari.satellitestracker.feature.details.databinding.SatelliteDetailsFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailsFragment : ViewModelFragment<SatelliteDetailsFragmentBinding, SatelliteDetailsViewModel>(
    R.layout.satellite_details_fragment,
    SatelliteDetailsViewModel::class.java
) {
    override fun getBindingLayout(v: View) = SatelliteDetailsFragmentBinding.bind(v)

    private val satelliteId: Int by this arg "id"
    private val satelliteName: String by this arg "name"

    override fun observers() {
        lifecycleScope.launch {
            viewModel.details.collectLatest {
                if (it == null) return@collectLatest

                with(binding) {
                    name.text = satelliteName
                    datetime.text = it.firstFlight
                    height.text = it.height.toString()
                    cost.text = it.costPerLaunch.toString()
                    position.text = "Loading..."
                }

            }
        }
    }

    override fun onFragmentCreated() {
        viewModel.get(satelliteId)
    }

}