package io.github.janbarari.satellitestracker.feature.list

import androidx.lifecycle.viewModelScope
import io.github.janbarari.satellitestracker.core.viewmodel.BaseViewModel
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SatellitesListViewModel @Inject constructor(
    private val getSatelliteListUseCase: GetSatelliteListUseCase
): BaseViewModel() {

    private val _satellites: MutableStateFlow<List<Satellite>> = MutableStateFlow(arrayListOf())
    val satellites: StateFlow<List<Satellite>> = _satellites

    init {
        viewModelScope.launch {
            getSatelliteListUseCase
                .execute()
                .collectLatest {
                    _satellites.value = it
                }
        }
    }

}