package com.medialink.archcompsubmission.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.data.model.tvshow.ListTvShow
import com.medialink.archcompsubmission.utils.DataDummy
import com.medialink.archcompsubmission.utils.getJsonDataFromAsset

class TvShowsRepository(context: Context) : IRepository {
    private var listTvShow: ListTvShow
    private val formatPoster = "https://www.themoviedb.org/t/p/w220_and_h330_face%1s"

    init {
        val gson = Gson()
        val jsonString = getJsonDataFromAsset(context, "list_tv_show.json")
        listTvShow = gson.fromJson(jsonString, ListTvShow::class.java)
        listTvShow.let { Log.i("tv_show", it.toString()) }
    }

    override fun getListData(): List<Detail> {
        val listResult = mutableListOf<Detail>()

        listTvShow.results?.let {
            for (item in it) {

                listResult.add(
                    Detail(
                        item.id,
                        item.firstAirDate,
                        String.format(formatPoster, item.posterPath),
                        item.name,
                        item.overview,
                        item.popularity,
                        item.voteAverage,
                        false
                    )
                )

            }
        }
        return listResult
    }

    override fun getCurrentData(id: Int): Detail {
        var itemDetail = Detail(
            -1, "", "", "", "",
            0.0, 0.0, false
        )


        listTvShow.results?.let {
            for (item in it) {
                if (item.id == id) {
                    itemDetail = Detail(
                        item.id,
                        item.firstAirDate,
                        String.format(formatPoster, item.posterPath),
                        item.name,
                        item.overview,
                        item.popularity,
                        item.voteAverage,
                        false
                    )
                    break
                }
            }
        }

        return itemDetail
    }
}