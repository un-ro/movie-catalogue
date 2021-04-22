package com.unero.moviecatalogue


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.unero.moviecatalogue.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun rvMovie() {
        Thread.sleep(2500)
        onView(withText("MOVIES")).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        onView(withText("Godzilla vs. Kong")).check(matches(isDisplayed()))
        onView(withText("24 March 2021")).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed())).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText("Black Water: Abyss"))))

        onView(withText("Black Water: Abyss")).check(matches(isDisplayed()))
        onView(withText("09 July 2020")).check(matches(isDisplayed()))
    }

    @Test
    fun rvTV() {
        Thread.sleep(2500)

        onView(withContentDescription("TV Shows")).perform(ViewActions.click())

        onView(withText("TV SHOWS")).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))

        onView(withText("The Falcon and the Winter...")).check(matches(isDisplayed()))
        onView(withText("19 March 2021")).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions
                        .scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText("Transformers: War for Cyb..."))))

        onView(withText("Transformers: War for Cyb...")).check(matches(isDisplayed()))
        onView(withText("30 July 2020")).check(matches(isDisplayed()))
    }

}
