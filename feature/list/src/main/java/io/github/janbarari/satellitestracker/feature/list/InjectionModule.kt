package io.github.janbarari.satellitestracker.feature.list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import io.github.janbarari.satellitestracker.data.asset.AssetFileProvider
import io.github.janbarari.satellitestracker.data.database.SatelliteTrackerDatabase
import io.github.janbarari.satellitestracker.data.mapper.SatelliteListMapper
import io.github.janbarari.satellitestracker.data.repository.SatelliteRepositoryImp
import io.github.janbarari.satellitestracker.data.source.SatelliteLocalSource
import io.github.janbarari.satellitestracker.data.source.imp.SatelliteLocalSourceImp
import io.github.janbarari.satellitestracker.domain.repository.SatelliteRepository
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteListUseCase
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object InjectionModule {


//    @Provides
//    fun provideSatelliteLocalSource(db: SatelliteTrackerDatabase, assetFileProvider: AssetFileProvider): SatelliteLocalSource {
//        return SatelliteLocalSourceImp(db.getSatelliteDAO(), assetFileProvider)
//    }
//
//    @Provides
//    fun provideSatelliteRepository(source: SatelliteLocalSource): SatelliteRepository {
//        return SatelliteRepositoryImp(source)
//    }

    @Provides
    fun provideGetSatelliteListUseCase(): GetSatelliteListUseCase {
        return GetSatelliteListUseCase()
    }

}