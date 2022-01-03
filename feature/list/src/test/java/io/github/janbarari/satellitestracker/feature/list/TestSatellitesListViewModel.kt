package io.github.janbarari.satellitestracker.feature.list

import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TestSatellitesListViewModel {

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `satellite list count is correct`() = runBlocking {
        val getSatellitesListUseCase = mockk<GetSatelliteListUseCase>()
        coEvery {
            getSatellitesListUseCase.execute()
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
        val viewModel = SatellitesListViewModel(getSatellitesListUseCase)
        assertEquals(3, viewModel.satellites.value.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}