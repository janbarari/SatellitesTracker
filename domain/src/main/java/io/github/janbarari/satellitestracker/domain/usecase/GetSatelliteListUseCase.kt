package io.github.janbarari.satellitestracker.domain.usecase

import io.github.janbarari.satellitestracker.domain.core.FlowUseCaseNoInput
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow

class GetSatelliteListUseCase(
    private val repo: SatelliteRepository
): FlowUseCaseNoInput<List<Satellite>>() {

    override suspend fun execute(): Flow<List<Satellite>> {
        return repo.getAll()
    }

}