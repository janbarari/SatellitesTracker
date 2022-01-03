package io.github.janbarari.satellitestracker.domain.usecase

import io.github.janbarari.satellitestracker.domain.core.FlowUseCase
import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import io.github.janbarari.satellitestracker.domain.repository.PositionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor(
    private val positionRepository: PositionRepository
) : FlowUseCase<String, List<PositionXY>?>() {
    override suspend fun execute(input: String): Flow<List<PositionXY>?> {
        return positionRepository.get(input)
    }
}