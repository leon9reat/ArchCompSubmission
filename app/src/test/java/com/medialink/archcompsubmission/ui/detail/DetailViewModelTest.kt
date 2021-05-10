package com.medialink.archcompsubmission.ui.detail

import com.medialink.archcompsubmission.ui.main.fragment.BaseFragment
import com.medialink.archcompsubmission.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/*
DetailViewModelTest
1. pastikan data detail yang dihasilkan tidak null
2. pastikan data movie / tv show sesuai dengan list
 */
class DetailViewModelTest {

    @Test
    fun checkDetailMovieBenar() {
        val detailViewModel = DetailViewModel(BaseFragment.PARAM_MOVIE)
        val detail = detailViewModel.getCurrentData(1)

        val expectedValue = DataDummy.generateMovies()[0]
        assertNotNull(detail)
        assertEquals(expectedValue, detail)
    }

    @Test
    fun checkDetailTvBenar() {
        val detailViewModel = DetailViewModel(BaseFragment.PARAM_TV_SHOW)
        val detail = detailViewModel.getCurrentData(1)

        val expectedValue = DataDummy.generateTvShows()[0]
        assertNotNull(detail)
        assertEquals(expectedValue, detail)
    }
}