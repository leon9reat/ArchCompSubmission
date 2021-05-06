package com.medialink.archcompsubmission.data.repository

import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.utils.DataDummy

class MoviesRepository : IRepository {
    val listMovie : List<Detail> = DataDummy.generateMovies()

    override fun getData(): List<Detail> {
        return listMovie
    }
}