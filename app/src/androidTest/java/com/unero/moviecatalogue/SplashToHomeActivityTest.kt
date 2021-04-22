package com.unero.moviecatalogue


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.unero.moviecatalogue.ui.splash.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashToHomeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun splashToHome() {
        // Wait Splash Screen
        Thread.sleep(2500)

        // Check Title
        onView(withId(R.id.title)).check(matches(withText("Movie Catalogue")))

        // Check Tabs
        onView(withText("MOVIES")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
    }


}
