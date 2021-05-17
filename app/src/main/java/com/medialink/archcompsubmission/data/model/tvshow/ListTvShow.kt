package com.medialink.archcompsubmission.data.model.tvshow

import com.google.gson.annotations.SerializedName


data class ListTvShow(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<TvShow>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

