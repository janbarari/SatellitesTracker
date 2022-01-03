package io.github.janbarari.satellitestracker.data.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.janbarari.satellitestracker.data.asset.AssetFileProvider
import io.github.janbarari.satellitestracker.data.database.SatelliteTrackerDatabase
import io.github.janbarari.satellitestracker.data.mapper.SatelliteListMapper
import io.github.janbarari.satellitestracker.data.repository.SatelliteRepositoryImp
import io.github.janbarari.satellitestracker.data.source.SatelliteLocalSource
import io.github.janbarari.satellitestracker.data.source.imp.SatelliteLocalSourceImp
import io.github.janbarari.satellitestracker.domain.repository.SatelliteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InjectionModule {

    @Provides
    @Singleton
    fun provideSatelliteTrackerDatabase(@ApplicationContext context: Context): SatelliteTrackerDatabase {
        return SatelliteTrackerDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideAssetFileProvider(@ApplicationContext context: Context, moshi: Moshi): AssetFileProvider {
        return AssetFileProvider(context, moshi)
    }

    @Provides
    @Singleton
    fun provideSatelliteListMapper(): SatelliteListMapper = SatelliteListMapper()

    @Provides
    fun provideSatelliteLocalSource(
        db: SatelliteTrackerDatabase,
        assetFileProvider: AssetFileProvider
    ): SatelliteLocalSource {
        return SatelliteLocalSourceImp(db.getSatelliteDAO(), assetFileProvider)
    }

    @Provides
    fun provideSatelliteRepository(
        satelliteLocalSource: SatelliteLocalSource,
        satelliteListMapper: SatelliteListMapper
    ): SatelliteRepository {
        return SatelliteRepositoryImp(satelliteLocalSource, satelliteListMapper)
    }

}