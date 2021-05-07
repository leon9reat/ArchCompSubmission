package com.medialink.archcompsubmission.ui.main.fragment

import com.medialink.archcompsubmission.data.model.Detail

interface BaseFragmentCallback {
    fun onItemClick(detail: Detail)
    fun onShareClick(detail: Detail)
    fun onFavoriteClick(detail: Detail)
}