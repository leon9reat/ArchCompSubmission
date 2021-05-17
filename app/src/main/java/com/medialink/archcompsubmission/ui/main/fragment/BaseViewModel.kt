package com.medialink.archcompsubmission.ui.main.fragment

import androidx.lifecycle.ViewModel
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.data.repository.IRepository
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository
import com.medialink.archcompsubmission.utils.getJsonDataFromAsset

class BaseViewModel(idJenis: Int): ViewModel() {

    var jsonString: String? = null

    private val mRepo: IRepository = when (idJenis) {
        BaseFragment.PARAM_MOVIE -> {
            MoviesRepository()
        }
        else -> TvShowsRepository()
    }

    fun getListData(): List<Detail> = mRepo.getListData()
}