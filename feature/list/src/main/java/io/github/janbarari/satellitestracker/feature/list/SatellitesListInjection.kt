package io.github.janbarari.satellitestracker.feature.list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import io.github.janbarari.satellitestracker.domain.repository.SatelliteRepository
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteListUseCase

@Module
@InstallIn(FragmentComponent::class)
object SatellitesListInjection {

    @Provides
    fun provideGetSatelliteListUseCase(satelliteRepository: SatelliteRepository): GetSatelliteListUseCase {
        return GetSatelliteListUseCase(satelliteRepository)
    }

    @Provides
    fun provideSatellitesListViewModel(getSatelliteListUseCase: GetSatelliteListUseCase): SatellitesListViewModel {
        return SatellitesListViewModel(getSatelliteListUseCase)
    }

}