package io.github.janbarari.satellitestracker.feature.details

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.satellitestracker.core.extensions.arg
import io.github.janbarari.satellitestracker.core.extensions.currencyFormat
import io.github.janbarari.satellitestracker.core.extensions.repeatJob
import io.github.janbarari.satellitestracker.core.fragment.ViewModelFragment
import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import io.github.janbarari.satellitestracker.feature.details.databinding.SatelliteDetailsFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailsFragment : ViewModelFragment<SatelliteDetailsFragmentBinding, SatelliteDetailsViewModel>(
    R.layout.satellite_details_fragment,
    SatelliteDetailsViewModel::class.java
) {
    override fun getBindingLayout(v: View) = SatelliteDetailsFragmentBinding.bind(v)

    private val satelliteId: Int by this arg "id"
    private val satelliteName: String by this arg "name"

    private val positions: ArrayList<PositionXY> = arrayListOf()
    private var positionUpdaterJob: Job? = null
    private var lastShownPositionIndex: Int = 0

    override fun observers() {

        viewLifecycleOwner.lifecycleScope.launch {

            launch {
                viewModel
                    .positions
                    .collectLatest {
                        if (it == null) {
                            binding.position.text = getString(R.string.loading)
                            return@collectLatest
                        }
                        positions.addAll(it)

                        if (positionUpdaterJob == null) {
                            positionUpdaterJob = repeatJob(3000) {
                                if (positions.isEmpty()) return@repeatJob
                                if (lastShownPositionIndex >= positions.size) {
                                    lastShownPositionIndex = 0
                                }
                                positions[lastShownPositionIndex].also {
                                    binding.position.text = "%s,%s".format(it.posX, it.posY)
                                }
                                lastShownPositionIndex++
                            }
                        }

                    }
            }

            launch {
                viewModel.details.collectLatest {
                    if (it == null) return@collectLatest

                    with(binding) {
                        name.text = satelliteName
                        datetime.text = it.firstFlight
                        height.text = it.height.toString()
                        cost.text = it.costPerLaunch.toString().currencyFormat()
                    }

                }
            }

        }

    }

    override fun onStop() {
        super.onStop()
        positionUpdaterJob?.cancel()
    }

    override fun onStart() {
        super.onStart()
        positionUpdaterJob?.start()
    }

    override fun onFragmentCreated() {
        viewModel.getDetails(satelliteId)
        viewModel.getPositions(satelliteId.toString())
    }

}