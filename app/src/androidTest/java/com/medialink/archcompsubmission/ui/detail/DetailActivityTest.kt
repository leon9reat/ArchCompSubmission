package com.medialink.archcompsubmission.ui.detail

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.CheckResult
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.medialink.archcompsubmission.R
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository
import com.medialink.archcompsubmission.ui.main.MainActivity
import com.medialink.archcompsubmission.ui.main.fragment.BaseFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var context: Context

    @Before
    fun setUp() {
       activityRule.scenario.onActivity {
           context = it.applicationContext
       }
    }


    @Test
    fun shouldMatchWithFirstMovieRecord() {
        val dummyMovie = MoviesRepository(context).getListData()

        activityRule.scenario.onActivity {
            dummyMovie[0].id?.let { id ->
                DetailActivity.showActivity(it, BaseFragment.PARAM_MOVIE,
                    id
                )
            }
        }

        onView(withId(R.id.tv_title_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie_detail)).check(matches(withText(dummyMovie[0].title)))

        onView(withId(R.id.tv_release_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_movie)).check(matches(withText(dummyMovie[0].releaseDate)))

        onView(withId(R.id.tv_vote_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_movie)).check(matches(withText(dummyMovie[0].voteAverage.toString())))

        val voteProgrees = (dummyMovie[0].voteAverage?.times(10)?.toInt() ?: 0)
        onView(withId(R.id.progress_vote_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_vote_movie)).check(matches(ProgressMatcher.withProgress(voteProgrees)))

        onView(withId(R.id.tv_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_movie)).check(matches(withText(dummyMovie[0].overview.toString())))
    }

    @Test
    fun shouldMatchWithFirstTvShowRecord() {

        val dummyTvShow = TvShowsRepository(context).getListData()

        activityRule.scenario.onActivity {
            dummyTvShow[0].id?.let { id ->
                DetailActivity.showActivity(it, BaseFragment.PARAM_TV_SHOW,
                    id
                )
            }
        }

        onView(withId(R.id.tv_title_movie_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie_detail)).check(matches(withText(dummyTvShow[0].title)))

        onView(withId(R.id.tv_release_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_movie)).check(matches(withText(dummyTvShow[0].releaseDate)))

        onView(withId(R.id.tv_vote_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_movie)).check(matches(withText(dummyTvShow[0].voteAverage.toString())))

        val voteProgrees = (dummyTvShow[0].voteAverage?.times(10)?.toInt() ?: 0)
        onView(withId(R.id.progress_vote_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_vote_movie)).check(matches(ProgressMatcher.withProgress(voteProgrees)))

        onView(withId(R.id.tv_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_movie)).check(matches(withText(dummyTvShow[0].overview.toString())))
    }

    class ProgressMatcher private constructor(private val progress: Int) :
        BoundedMatcher<View?, ProgressBar>(ProgressBar::class.java) {
        override fun matchesSafely(progressBar: ProgressBar): Boolean {
            return progressBar.progress == progress
        }

        companion object {
            /**
             * Matches that the given progress is displayed.
             *
             *
             * Example usage:
             * `onView(withId(R.id.view)).check(matches(withProgress(2)));`
             */
            @CheckResult
            fun withProgress(progress: Int): ProgressMatcher {
                return ProgressMatcher(progress)
            }
        }

        override fun describeTo(description: org.hamcrest.Description) {
            description.appendText("has progress: ").appendValue(progress)
        }
    }

}