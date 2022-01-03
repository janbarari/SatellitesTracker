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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataInjectionModule {

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

}