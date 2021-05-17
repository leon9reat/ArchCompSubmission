package com.medialink.archcompsubmission.ui.main.fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.data.repository.IRepository
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository

class BaseViewModel(context: Context, idJenis: Int): ViewModel() {

    private val mRepo: IRepository = when (idJenis) {
        BaseFragment.PARAM_MOVIE -> MoviesRepository(context)
        else -> TvShowsRepository(context)
    }

    fun getListData(): List<Detail> = mRepo.getListData()
}