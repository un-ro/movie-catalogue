package com.unero.moviecatalogue


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.unero.moviecatalogue.ui.home.HomeActivity
import com.unero.moviecatalogue.util.IdlingResources
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResources.idlingResource)
    }

    @Test
    fun loadHome() {
        onView(allOf(withText("Movie Catalogue"), isDisplayed()))
        onView(allOf(withId(R.id.item_favorite), isDisplayed()))
        onView(allOf(withText("MOVIES"), isDisplayed()))
        onView(allOf(withId(R.id.rv_movie), isDisplayed()))

        onView(withContentDescription("TV Shows")).perform(click())
        onView(withText("TV SHOWS")).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun loadFavorite() {
        onView(allOf(withId(R.id.item_favorite), isDisplayed())).perform(click())
        onView(allOf(withText("Favorite"), isDisplayed()))
        onView(allOf(withText("MOVIES"), isDisplayed()))
        onView(allOf(withText("TVSHOW"), isDisplayed()))
        onView(allOf(withContentDescription("Navigate up"), isDisplayed()))
    }

    @Test
    fun loadDetail() {
        // Home
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(allOf(withId(R.id.iv_poster), isDisplayed()))
        onView(allOf(withId(R.id.tv_title), isDisplayed()))
        onView(allOf(withId(R.id.tv_meta), isDisplayed()))
        onView(allOf(withId(R.id.rtb_rate), isDisplayed()))
        onView(allOf(withId(R.id.cg_genres), isDisplayed()))
        onView(allOf(withId(R.id.tv_overview), isDisplayed()))
        onView(allOf(withId(R.id.fab_fav), isDisplayed()))
    }
}
