package com.medialink.archcompsubmission.ui.main.fragment

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/*
BaseViewModelTest
1. pastikan list data movie tidak null
2. pastikan ukuran list movie sama dengan dummy
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.Q])
class BaseViewModelTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun test_JumlahDataMovie() {
        val baseViewModel = BaseViewModel(context, BaseFragment.PARAM_MOVIE) // ambil dari view model
        val listData = MoviesRepository(context).getListData() // ambil langsung dari repository

        val expectedValue = listData.size
        assertNotNull(baseViewModel)
        assertEquals(expectedValue, baseViewModel.getListData().size)
    }

    @Test
    fun test_JumlahDataTvShow() {
        val baseViewModel = BaseViewModel(context, BaseFragment.PARAM_TV_SHOW).getListData() // ambil data dari view model
        val listData = TvShowsRepository(context).getListData() // ambil data dari repository

        val expectedValue = listData.size
        assertNotNull(baseViewModel)
        assertEquals(expectedValue, baseViewModel.size)
    }

    @Test
    fun test_DataMoviePertamaBenar() {
        val dataViewModel = BaseViewModel(context, BaseFragment.PARAM_MOVIE).getListData()[0]
        val dataRepository = MoviesRepository(context).getListData()[0]

        assertNotNull(dataViewModel)
        assertEquals(dataViewModel.id, dataRepository.id)
        assertEquals(dataViewModel.title, dataRepository.title)
        assertEquals(dataViewModel.overview, dataRepository.overview)
        assertEquals(dataViewModel.voteAverage, dataRepository.voteAverage)
    }

    @Test
    fun test_DataTvShowPertamaBenar() {
        val dataViewModel = BaseViewModel(context, BaseFragment.PARAM_TV_SHOW).getListData()[0]
        val dataRepository = TvShowsRepository(context).getListData()[0]

        assertNotNull(dataViewModel)
        assertEquals(dataViewModel.id, dataRepository.id)
        assertEquals(dataViewModel.title, dataRepository.title)
        assertEquals(dataViewModel.overview, dataRepository.overview)
        assertEquals(dataViewModel.voteAverage, dataRepository.voteAverage)
    }
}