package io.github.janbarari.satellitestracker.feature.details

import androidx.lifecycle.viewModelScope
import io.github.janbarari.satellitestracker.core.viewmodel.BaseViewModel
import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails
import io.github.janbarari.satellitestracker.domain.usecase.GetPositionsUseCase
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SatelliteDetailsViewModel @Inject constructor(
    private val getSatelliteDetailsUseCase: GetSatelliteDetailsUseCase,
    private val getPositionsUseCase: GetPositionsUseCase
): BaseViewModel() {

    private val _details: MutableStateFlow<SatelliteDetails?> = MutableStateFlow(null)
    val details: StateFlow<SatelliteDetails?> = _details

    private val _positions: MutableStateFlow<List<PositionXY>?> = MutableStateFlow(null)
    val positions: StateFlow<List<PositionXY>?> = _positions

    fun getDetails(id: Int) {
        viewModelScope.launch {
            getSatelliteDetailsUseCase
                .execute(id)
                .collectLatest {
                    _details.emit(it)
                }
        }
    }

    fun getPositions(id: String) {
        viewModelScope.launch {
            getPositionsUseCase
                .execute(id)
                .collectLatest {
                    _positions.emit(it)
                }
        }
    }

}