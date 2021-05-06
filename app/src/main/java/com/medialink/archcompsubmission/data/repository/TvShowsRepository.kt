package com.medialink.archcompsubmission.data.repository

import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.utils.DataDummy

class TvShowsRepository(): IRepository {
    private val listDetail: List<Detail> = DataDummy.generateTvShows()

    override fun getData(): List<Detail> {
        return listDetail
    }
}