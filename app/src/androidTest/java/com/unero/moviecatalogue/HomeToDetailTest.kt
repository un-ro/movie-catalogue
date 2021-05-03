package com.unero.moviecatalogue


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.unero.moviecatalogue.ui.home.HomeActivity
import com.unero.moviecatalogue.util.IdlingResources
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeToDetailTest {

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
    fun homeToDetail() {
        // Home
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Into Detail Activity
        onView(
                allOf(withText("Movie"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.appbar)))),
                        isDisplayed()))
                .check(matches(withText("Movie")))

        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_meta)).check(matches(isDisplayed()))
        onView(withId(R.id.rtb_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.cg_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun homeToDetailToHome() {
        // Home
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Detail
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_meta)).check(matches(isDisplayed()))

        onView(allOf(withContentDescription("Navigate up"),
                childAtPosition(allOf(withId(R.id.toolbar),
                        childAtPosition(withId(R.id.appbar), 0)), 2),
                        isDisplayed()
                )
        ).perform(click())

        // Home Again
        onView(withText("Movie Catalogue")).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
