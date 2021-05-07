package com.medialink.archcompsubmission.ui.detail

import androidx.lifecycle.ViewModel
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.data.repository.IRepository
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository
import com.medialink.archcompsubmission.ui.main.fragment.BaseFragment

class DetailViewModel(idJenis: Int): ViewModel() {
    private val mRepo: IRepository = when (idJenis) {
        BaseFragment.PARAM_MOVIE -> MoviesRepository()
        else -> TvShowsRepository()
    }

    fun getCurrentData(id: Int): Detail = mRepo.getCurrentData(id)
}