package com.medialink.archcompsubmission.ui.main

import android.provider.ContactsContract.Directory.PACKAGE_NAME
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.google.android.material.tabs.TabLayout
import com.medialink.archcompsubmission.R
import com.medialink.archcompsubmission.ui.detail.DetailActivity
import com.medialink.archcompsubmission.ui.main.fragment.BaseFragment
import com.medialink.archcompsubmission.utils.DataDummy
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    private val dummyMovies = DataDummy.generateMovies()
    private val dummyTvShow = DataDummy.generateTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)



    @Test
    fun loadMovie() {
        val matcher = allOf(
            withId(R.id.movies_rv),
            isDisplayed()
        )

        onView(matcher).check(matches(isDisplayed()))
        onView(matcher).perform(
            swipeUp()
        )

    }

    @Test
    fun loadTvShow() {
        val tabs = allOf(
            withText("Tv Shows"),
            isDescendantOfA(withId(R.id.tabs))
        )
        onView(tabs).perform(click())
        onView(withId(R.id.tabs)).check(matches(matchCurrentTabTitle("Tv Shows")))

        val matcher = allOf(
            withId(R.id.movies_rv),
            isDisplayed(),
        )
        onView(matcher).check(matches(isDisplayed()))
        onView(matcher).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun shouldShowMovieDetail() {
        val matcher = allOf(
            withId(R.id.movies_rv),
            isDisplayed(),
        )
        onView(matcher).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        intended(allOf(
            hasComponent(hasShortClassName(".ui.detail.DetailActivity")),
            hasExtra(DetailActivity.EXTRA_JENIS, BaseFragment.PARAM_MOVIE),
            hasExtra(DetailActivity.EXTRA_ID, dummyMovies[0].id)))
    }

    @Test
    fun shouldShowTvShowDetail() {
        val tabs = allOf(
            withText("Tv Shows"),
            isDescendantOfA(withId(R.id.tabs))
        )
        onView(tabs).perform(click())
        onView(withId(R.id.tabs)).check(matches(matchCurrentTabTitle("Tv Shows")))

        val matcher = allOf(
            withId(R.id.movies_rv),
            isDisplayed()
        )
        onView(matcher).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        intended(allOf(
            hasComponent(hasShortClassName(".ui.detail.DetailActivity")),
            hasExtra(DetailActivity.EXTRA_JENIS, BaseFragment.PARAM_TV_SHOW),
            hasExtra(DetailActivity.EXTRA_ID, dummyTvShow[0].id)))
    }

    @Test
    fun shouldShowTvShowTab() {
        // swipe kiri, pastikan title benar
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.tabs)).check(matches(matchCurrentTabTitle("Tv Shows")))

        /*onView(allOf(withId(R.id.movies_rv), hasFocus()))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<BaseAdapter.MovieViewHolder>(2, click())) */

    }

    @Test
    fun shouldClickTvShow_swipeRightToMovie() {
        // klik tab tv show
        val matcher = allOf(
            withText("Tv Shows"),
            isDescendantOfA(withId(R.id.tabs))
        )
        onView(matcher).perform(click())

        // swipe kanan
        onView(withId(R.id.view_pager)).perform(ViewActions.swipeRight())
        onView(withId(R.id.tabs)).check(matches(matchCurrentTabTitle("Movies")))
    }

    private fun matchCurrentTabTitle(tabTitle: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("unable to match title of current selected tab with $tabTitle")
            }

            override fun matchesSafely(item: View?): Boolean {
                val tabLayout = item as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabLayout.selectedTabPosition)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index ${tabLayout.selectedTabPosition}"))
                        .build()

                return tabAtIndex.text.toString().contains(tabTitle, true)
            }
        }
    }

}