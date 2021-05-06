package com.medialink.archcompsubmission.ui.main.fragment

import androidx.lifecycle.ViewModel
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.data.repository.IRepository

class MovieViewModel(val repository: IRepository): ViewModel() {
    fun getData(): List<Detail> = repository.getData()
}