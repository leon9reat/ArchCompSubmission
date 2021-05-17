package com.medialink.archcompsubmission.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.data.repository.IRepository
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository
import com.medialink.archcompsubmission.ui.main.fragment.BaseFragment



class DetailViewModel(context: Context, idJenis: Int): ViewModel() {
    private val mRepo: IRepository = when (idJenis) {
        BaseFragment.PARAM_MOVIE -> MoviesRepository(context)
        else -> TvShowsRepository(context)
    }

    fun getCurrentData(id: Int): Detail = mRepo.getCurrentData(id)
}