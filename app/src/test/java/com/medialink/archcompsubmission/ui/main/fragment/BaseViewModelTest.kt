package com.medialink.archcompsubmission.ui.main.fragment

import com.medialink.archcompsubmission.utils.DataDummy
import org.junit.Assert.*
import org.junit.Test

/*
BaseViewModelTest
1. pastikan list data movie tidak null
2. pastikan ukuran list movie sama dengan dummy
 */
class BaseViewModelTest {
    @Test
    fun checkDetailMovieBenar() {
        val baseViewModel = BaseViewModel(BaseFragment.PARAM_MOVIE)
        val listData = baseViewModel.getListData()

        val expectedValue = DataDummy.generateMovies().size
        assertNotNull(listData)
        assertEquals(expectedValue, listData.size)
    }

    @Test
    fun checkDetailTvBenar() {
        val baseViewModel = BaseViewModel(BaseFragment.PARAM_TV_SHOW)
        val listData = baseViewModel.getListData()

        val expectedValue = DataDummy.generateTvShows().size
        assertNotNull(listData)
        assertEquals(expectedValue, listData.size)
    }
}