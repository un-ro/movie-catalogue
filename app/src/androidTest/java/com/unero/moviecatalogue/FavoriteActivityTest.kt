package com.unero.moviecatalogue


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.unero.moviecatalogue.ui.home.HomeActivity
import com.unero.moviecatalogue.util.IdlingResources
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class FavoriteActivityTest {

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
    fun favoriteActivityTest() {
        onView(withId(R.id.item_favorite)).perform(click())

        onView(withText("MOVIES")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
        onView(withText("Favorite")).check(matches(isDisplayed()))

        onView(allOf(withContentDescription("Navigate up"),
            withParent(allOf(withId(R.id.toolbar))),
            isDisplayed()))
            .check(matches(isDisplayed()))
    }
}
