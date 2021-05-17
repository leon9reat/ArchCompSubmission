package com.medialink.archcompsubmission.ui.detail

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository
import com.medialink.archcompsubmission.ui.main.fragment.BaseFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/*
DetailViewModelTest
1. pastikan data detail yang dihasilkan tidak null
2. pastikan data movie / tv show sesuai dengan list
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.Q])
class DetailViewModelTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun test_DetailPertamaMovie() {
        val repoMovie =
            MoviesRepository(context).getListData()[0] // ambil rec pertama dari repository
        assertNotNull(repoMovie.id)

        val movie =
            repoMovie.id?.let {
                DetailViewModel(
                    context,
                    BaseFragment.PARAM_MOVIE
                ).getCurrentData(it)  // ambil data dari view model
            }

        assertNotNull(movie)
        assertEquals(repoMovie.id, movie?.id)
        assertEquals(repoMovie.title, movie?.title)
        assertEquals(repoMovie.releaseDate, movie?.releaseDate)
        assertEquals(repoMovie.overview, movie?.overview)
    }

    @Test
    fun test_DetailPertamaTvShow() {
        val repoTvShow =
            TvShowsRepository(context).getListData()[0] // ambil rec pertama dari repository
        assertNotNull(repoTvShow.id)

        val tvShow =
            repoTvShow.id?.let {
                DetailViewModel(
                    context,
                    BaseFragment.PARAM_TV_SHOW
                ).getCurrentData(it)  // ambil data dari view model
            }

        assertNotNull(tvShow)
        assertEquals(repoTvShow.id, tvShow?.id)
        assertEquals(repoTvShow.title, tvShow?.title)
        assertEquals(repoTvShow.releaseDate, tvShow?.releaseDate)
        assertEquals(repoTvShow.overview, tvShow?.overview)
    }
}