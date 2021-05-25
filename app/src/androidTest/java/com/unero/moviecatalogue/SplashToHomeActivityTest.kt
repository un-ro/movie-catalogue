package com.unero.moviecatalogue


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.unero.moviecatalogue.ui.splash.SplashActivity
import com.unero.moviecatalogue.util.IdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashToHomeActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResources.idlingResource)
    }

    @Test
    fun splashToHome() {
        // Check Title
        onView(withId(R.id.title)).check(matches(withText("Movie Catalogue")))
        // Check Tabs
        onView(withText("MOVIES")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
    }
}
