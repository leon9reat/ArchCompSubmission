package com.medialink.archcompsubmission.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medialink.archcompsubmission.ui.detail.DetailViewModel
import com.medialink.archcompsubmission.ui.main.fragment.BaseViewModel

object DataFactory {
    fun getFactory(context: Context, idJenis: Int) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BaseViewModel(context, idJenis) as T
        }
    }

    fun getDetailFactory(context: Context, idJenis: Int): ViewModelProvider.Factory {
       return object : ViewModelProvider.Factory {
           override fun <T : ViewModel?> create(modelClass: Class<T>): T {
               return DetailViewModel(context, idJenis) as T
           }
       }
    }
}