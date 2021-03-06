package io.github.janbarari.satellitestracker.domain.usecase

import io.github.janbarari.satellitestracker.domain.core.FlowUseCase
import io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails
import io.github.janbarari.satellitestracker.domain.repository.SatelliteDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetSatelliteDetailsUseCase(
    private val repo: SatelliteDetailsRepository
): FlowUseCase<Int, SatelliteDetails?>() {
    override suspend fun execute(input: Int): Flow<SatelliteDetails?> {
        return repo.get(input)
    }
}