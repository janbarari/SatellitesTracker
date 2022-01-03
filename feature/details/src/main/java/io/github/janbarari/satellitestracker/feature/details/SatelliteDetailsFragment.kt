package io.github.janbarari.satellitestracker.feature.details

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.satellitestracker.core.extensions.*
import io.github.janbarari.satellitestracker.core.fragment.ViewModelFragment
import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import io.github.janbarari.satellitestracker.feature.details.databinding.SatelliteDetailsFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailsFragment : ViewModelFragment<SatelliteDetailsFragmentBinding, SatelliteDetailsViewModel>(
    R.layout.satellite_details_fragment,
    SatelliteDetailsViewModel::class.java
) {
    override fun getBindingLayout(v: View) = SatelliteDetailsFragmentBinding.bind(v)

    private val satelliteId: Int? by this arg "id"
    private val satelliteName: String? by this arg "name"

    private val positions: ArrayList<PositionXY> = arrayListOf()
    private var positionUpdaterJob: Job? = null
    private var lastShownPositionIndex: Int = 0

    override fun views() {

        if (satelliteId == null || satelliteName == null) {
            binding.tabletMessage.visible()
            binding.position.gone()
            binding.cost.gone()
            binding.positionLabel.gone()
            binding.costLabel.gone()
            binding.height.gone()
            binding.heightLabel.gone()
            binding.datetime.gone()
            binding.name.gone()
            return
        }

        binding.tabletMessage.gone()
        binding.position.visible()
        binding.cost.visible()
        binding.positionLabel.visible()
        binding.costLabel.visible()
        binding.height.visible()
        binding.heightLabel.visible()
        binding.datetime.visible()
        binding.name.visible()

    }

    override fun observers() {

        viewLifecycleOwner.lifecycleScope.launch {

            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
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
                                        binding.position.text = "%s\n%s".format(it.posX, it.posY)
                                    }
                                    lastShownPositionIndex++
                                }
                            }

                        }
                }
            }

            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
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
        if (satelliteId == null) return
        viewModel.getDetails(satelliteId!!)
        viewModel.getPositions(satelliteId.toString())
    }

}