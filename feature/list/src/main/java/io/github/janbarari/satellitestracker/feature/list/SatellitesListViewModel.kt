package io.github.janbarari.satellitestracker.feature.list

import androidx.lifecycle.viewModelScope
import io.github.janbarari.satellitestracker.core.viewmodel.BaseViewModel
import io.github.janbarari.satellitestracker.data.mapper.SatelliteListMapper
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class SatellitesListViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var satelliteListMapper: SatelliteListMapper

    private val _satellites: MutableStateFlow<List<Satellite>> = MutableStateFlow(arrayListOf())
    val satellites: StateFlow<List<Satellite>> = _satellites

    init {
        viewModelScope.launch {

            satelliteListMapper.map(arrayListOf())

            GetSatelliteListUseCase()
                .execute()
                .collectLatest {
                    _satellites.value = it
                }
        }
    }

}