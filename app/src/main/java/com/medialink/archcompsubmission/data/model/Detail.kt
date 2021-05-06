package com.medialink.archcompsubmission.data.model

data class Detail(
    var id: Int?,
    var releaseDate: String? = null,
    var posterPath: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var popularity: Double? = 0.0,
    var voteAverage: Double? = 0.0,
    var isFavorite: Boolean = false
)
