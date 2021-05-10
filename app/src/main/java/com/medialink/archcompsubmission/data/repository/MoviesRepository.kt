package com.medialink.archcompsubmission.data.repository

import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.utils.DataDummy

class MoviesRepository : IRepository {
    private val listMovie: List<Detail> = DataDummy.generateMovies()

    override fun getListData(): List<Detail> {
        return listMovie
    }

    override fun getCurrentData(id: Int): Detail {
        var itemDetail = Detail(-1, "", "", "", "",
            0.0, 0.0, false)


        for (item in listMovie) {
            if (item.id == id) {
                itemDetail = item
                break
            }
        }
        return itemDetail
    }
}