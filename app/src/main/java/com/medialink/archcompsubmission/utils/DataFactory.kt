package com.medialink.archcompsubmission.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medialink.archcompsubmission.ui.detail.DetailViewModel
import com.medialink.archcompsubmission.ui.main.fragment.BaseViewModel

object DataFactory {
    fun getFactory(idJenis: Int) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BaseViewModel(idJenis) as T
        }
    }

    fun getDetailFactory(idJenis: Int): ViewModelProvider.Factory {
       return object : ViewModelProvider.Factory {
           override fun <T : ViewModel?> create(modelClass: Class<T>): T {
               return DetailViewModel(idJenis) as T
           }
       }
    }
}