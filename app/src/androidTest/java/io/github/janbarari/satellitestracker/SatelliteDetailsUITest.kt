package io.github.janbarari.satellitestracker

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.janbarari.satellitestracker.feature.list.adapter.SatelliteViewHolder
import io.github.janbarari.satellitestracker.host.HostActivity
import org.hamcrest.core.AnyOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4::class)
class SatelliteDetailsUITest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<HostActivity>()

    @Test
    fun `test details`() {
        wait(1)
        onView(
            AnyOf.anyOf(
                ViewMatchers.withText("Starship-1")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        wait(1)
        onView(
            withId(
                R.id.list
            )
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<SatelliteViewHolder>(0, click())
        )
        wait(1)
        onView(
            AnyOf.anyOf(
                ViewMatchers.withText("Starship-1")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(
            AnyOf.anyOf(
                ViewMatchers.withText("2021-12-01")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(
            AnyOf.anyOf(
                ViewMatchers.withText("118")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }


}