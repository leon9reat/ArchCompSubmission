package com.medialink.archcompsubmission.data.repository

import com.medialink.archcompsubmission.data.model.Detail

interface IRepository {
    fun getListData(): List<Detail>
    fun getCurrentData(id: Int): Detail
}