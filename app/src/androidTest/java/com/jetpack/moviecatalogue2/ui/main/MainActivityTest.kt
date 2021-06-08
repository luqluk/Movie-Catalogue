package com.jetpack.moviecatalogue2.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.jetpack.moviecatalogue2.R
import com.jetpack.moviecatalogue2.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)

        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun movie_and_tv_list() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )

        onView(withId(R.id.navigation_tv)).perform(click())

        onView(withId(R.id.rv_tv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )

    }

    @Test
    fun detail_loaded() {
        onView(withId(R.id.rv_movie)).apply {
            check(matches(isDisplayed()))
            perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        }
        check_detail_components()

        Espresso.pressBack()

        onView(withId(R.id.navigation_tv)).perform(click())

        onView(withId(R.id.rv_tv)).apply {
            check(matches(isDisplayed()))
            perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        }
        check_detail_components()
    }

    @Test
    fun insert_update_favorite() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        Espresso.pressBack()

        onView(withId(R.id.navigation_tv)).perform(click())

        onView(withId(R.id.rv_tv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        Espresso.pressBack()

        onView(withId(R.id.navigation_fav)).perform(click())

        onView(withId(R.id.rv_movie_fav))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.empty_movie)).check(matches(isDisplayed()))

        onView(withText(R.string.tv_show_favorite)).perform(click())
        onView(withId(R.id.rv_tv_fav))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_favorite)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.empty)).check(matches(isDisplayed()))
    }

    private fun check_detail_components() {
        onView(withId(R.id.detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_score))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_vote))
            .check(matches(isDisplayed()))
        onView(withId(R.id.languange))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview))
        onView(withId(R.id.background_img_detail))
            .check(matches(isDisplayed()))
    }
}