package io.github.janbarari.satellitestracker

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.janbarari.satellitestracker.host.HostActivity
import org.hamcrest.core.AnyOf.anyOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4::class)
class SatelliteListUITest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<HostActivity>()

    @Test
    fun `test satellite list data is showing`() {
        wait(1)
        onView(
            anyOf(
                withText("Starship-1")
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun `test search behaviour`() {
        wait(1)
        onView(withId(R.id.search_view)).perform(click())
        onView(withId(R.id.search_view)).perform(SearchViewActionExtension.submitText("dragon"))
        wait(1)
        onView(anyOf(withText("Dragon-1"))).check(matches(isDisplayed()))
        onView(withId(R.id.list)).check(RecyclerViewItemCountAssertion(1))
    }

}