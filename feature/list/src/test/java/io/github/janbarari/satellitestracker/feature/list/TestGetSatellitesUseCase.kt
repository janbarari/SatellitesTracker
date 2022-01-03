package io.github.janbarari.satellitestracker.feature.list

import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.repository.SatelliteRepository
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class TestGetSatellitesUseCase {

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `satellite list count is correct`() = runBlocking {
        val repo = mockk<SatelliteRepository>()
        coEvery {
            repo.getAll()
        }.returns(
            flow {
                emit(
                    listOf(
                        Satellite(1, true, "Starship-1"),
                        Satellite(2, false, "Dragon-1"),
                        Satellite(3, false, "SpaceX-2")
                    )
                )
            }
        )
        val getSatelliteListUseCase = GetSatelliteListUseCase(repo)
        Assert.assertEquals(3, getSatelliteListUseCase.execute().single().size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}