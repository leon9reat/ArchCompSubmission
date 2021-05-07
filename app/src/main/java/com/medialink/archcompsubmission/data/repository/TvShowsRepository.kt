package com.medialink.archcompsubmission.data.repository

import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.utils.DataDummy

class TvShowsRepository : IRepository {
    private val listTvShow: List<Detail> = DataDummy.generateTvShows()

    override fun getListData(): List<Detail> {
        return listTvShow
    }

    override fun getCurrentData(id: Int): Detail {
        lateinit var itemDetail: Detail
        for (item in listTvShow) {
            if (item.id == id) {
                itemDetail = item
                break
            }
        }
        return itemDetail
    }
}