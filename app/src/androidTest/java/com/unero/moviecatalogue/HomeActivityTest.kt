package com.unero.moviecatalogue


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.unero.moviecatalogue.ui.home.HomeActivity
import com.unero.moviecatalogue.util.IdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class HomeActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResources.idlingResource)
    }

    @Test
    fun rvMovie() {
        onView(withText("MOVIES")).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun rvTV() {
        onView(withContentDescription("TV Shows")).perform(ViewActions.click())
        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
    }

}
