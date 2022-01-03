package io.github.janbarari.satellitestracker.feature.details

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import io.github.janbarari.satellitestracker.domain.repository.PositionRepository
import io.github.janbarari.satellitestracker.domain.repository.SatelliteDetailsRepository
import io.github.janbarari.satellitestracker.domain.usecase.GetPositionsUseCase
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteDetailsUseCase

@Module
@InstallIn(FragmentComponent::class)
object SatelliteDetailsInjection {

    @Provides
    fun provideGetSatelliteDetailsUseCase(repo: SatelliteDetailsRepository): GetSatelliteDetailsUseCase {
        return GetSatelliteDetailsUseCase(repo)
    }

    @Provides
    fun provideGetPositionsUseCase(repo: PositionRepository): GetPositionsUseCase {
        return GetPositionsUseCase(repo)
    }

    @Provides
    fun provideViewModel(
        getSatelliteDetailsUseCase: GetSatelliteDetailsUseCase,
        getPositionsUseCase: GetPositionsUseCase
    ): SatelliteDetailsViewModel {
        return SatelliteDetailsViewModel(getSatelliteDetailsUseCase, getPositionsUseCase)
    }

}