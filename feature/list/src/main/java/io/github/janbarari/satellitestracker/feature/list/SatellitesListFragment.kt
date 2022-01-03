package io.github.janbarari.satellitestracker.feature.list

import android.net.Uri
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.janbarari.satellitestracker.core.extensions.queryTextEvents
import io.github.janbarari.satellitestracker.core.fragment.ViewModelFragment
import io.github.janbarari.satellitestracker.feature.list.adapter.SatellitesAdapter
import io.github.janbarari.satellitestracker.feature.list.databinding.SatellitesListFragmentBinding
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
            gotoSatelliteDetails(it.id, it.name)
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

    private fun gotoSatelliteDetails(id: Int, name: String) {
        val deepLink = Uri.parse("myApp://satellite_details/$id/$name")
        if(resources.getBoolean(R.bool.isTablet)) {
            val navHostFragment = requireActivity()
                .supportFragmentManager
                .findFragmentByTag("nav_detail_fragment") as NavHostFragment
            navHostFragment.navController.navigate(deepLink)
        } else {
            findNavController().navigate(deepLink)
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
                searchByName(event.query.toString())
            }
            .launchIn(lifecycleScope)

    }

    private fun searchByName(query: String) {
        viewModel.satellites.value.filter {
            it.name.replace(" ", "").lowercase().contains(
                query.replace(" ", "").lowercase()
            )
        }.also {
            satellitesAdapter.updateList(it)
        }
    }

    private fun isSearchQueryExists(): Boolean {
        return binding.searchView.query.toString().isNotBlank()
    }

    private fun getSearchQuery(): String {
        return binding.searchView.query.toString()
    }

    override fun observers() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.satellites.collectLatest {

                    binding.progressBar.show()
                    delay(400)
                    binding.progressBar.hide()

                    if (isSearchQueryExists()) {
                        searchByName(getSearchQuery())
                    } else {
                        satellitesAdapter.updateList(it)
                    }
                }
            }
        }

    }

}