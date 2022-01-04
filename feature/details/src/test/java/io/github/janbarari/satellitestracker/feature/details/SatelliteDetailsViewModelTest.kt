package io.github.janbarari.satellitestracker.feature.details

import io.github.janbarari.satellitestracker.domain.entity.PositionXY
import io.github.janbarari.satellitestracker.domain.entity.Satellite
import io.github.janbarari.satellitestracker.domain.entity.SatelliteDetails
import io.github.janbarari.satellitestracker.domain.usecase.GetPositionsUseCase
import io.github.janbarari.satellitestracker.domain.usecase.GetSatelliteDetailsUseCase
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
class SatelliteDetailsViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `satellite list count is correct`() = runBlocking {

        val getSatellitesListUseCase = mockk<GetSatelliteDetailsUseCase>()
        coEvery {
            getSatellitesListUseCase.execute(1)
        }.returns(
            flow {
                emit(
                    SatelliteDetails(1, 10000, "2021-12-02", 100, 399)
                )
            }
        )

        val getPositionsUseCase = mockk<GetPositionsUseCase>()
        coEvery {
            getPositionsUseCase.execute("1")
        }.returns(
            flow {
                emit(
                    listOf(
                        PositionXY(0.4, 9.4),
                        PositionXY(0.3, 11.4),
                        PositionXY(0.1, 2.4),
                    )
                )
            }
        )


        val viewModel = SatelliteDetailsViewModel(getSatellitesListUseCase, getPositionsUseCase)
        viewModel.getDetails(1)
        viewModel.getPositions("1")

        assertEquals(100, viewModel.details.value?.height)
        assertEquals(3, viewModel.positions.value?.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}