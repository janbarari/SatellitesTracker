package io.github.janbarari.satellitestracker.feature.list

import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.satellitestracker.core.extensions.queryTextEvents
import io.github.janbarari.satellitestracker.core.fragment.ViewModelFragment
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.feature.list.adapter.SatellitesAdapter
import io.github.janbarari.satellitestracker.feature.list.databinding.SatellitesListFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

@AndroidEntryPoint
class SatellitesListFragment : ViewModelFragment<SatellitesListFragmentBinding, SatellitesListViewModel>(
    R.layout.satellites_list_fragment,
    SatellitesListViewModel::class.java
) {
    override fun getBindingLayout(v: View) = SatellitesListFragmentBinding.bind(v)

    @Inject
    @Named("vertical")
    lateinit var llm: Provider<LinearLayoutManager>
    lateinit var satellitesAdapter: SatellitesAdapter

    override fun views() {

        satellitesAdapter = SatellitesAdapter(viewModel.satellites.value) {
            findNavController().navigate(Uri.parse("myApp://satellite_details/${it.id}"))
        }

        binding.list.apply {
            layoutManager = llm.get()
            adapter = satellitesAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }

    override fun listeners() {

        binding.searchView
            .queryTextEvents()
            .debounce(300)
            .distinctUntilChanged()
            .onEach { event ->
                if (event.query.isBlank()) {
                    satellitesAdapter.reset(viewModel.satellites.value)
                    return@onEach
                }
                viewModel.satellites.value.filter {
                    it.name.toString().replace(" ", "").lowercase().contains(
                        event.query.toString().replace(" ", "").lowercase()
                    )
                }.also {
                    satellitesAdapter.updateList(it)
                }
            }
            .launchIn(lifecycleScope)

    }

    override fun observers() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.satellites.collectLatest {
                binding.progressBar.show()
                delay(1500)
                satellitesAdapter.updateList(it)
                binding.progressBar.hide()
            }
        }

    }

}