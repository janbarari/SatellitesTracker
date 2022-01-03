package io.github.janbarari.satellitestracker.domain.usecase

import io.github.janbarari.satellitestracker.domain.core.FlowUseCaseNoInput
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSatelliteListUseCase @Inject constructor(
    private val repo: SatelliteRepository
): FlowUseCaseNoInput<List<Satellite>>() {

    override suspend fun execute(): Flow<List<Satellite>> {
        return repo.getAll()
    }

}