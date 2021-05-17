package com.medialink.archcompsubmission.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.data.model.movie.ListMovie
import com.medialink.archcompsubmission.utils.getJsonDataFromAsset

class MoviesRepository(context: Context) : IRepository {

    private var listMovie: ListMovie
    private val formatPoster = "https://www.themoviedb.org/t/p/w220_and_h330_face%1s"

    init {
        val gson = Gson()
        val jsonString = getJsonDataFromAsset(context, "list_movie.json")
        listMovie = gson.fromJson(jsonString, ListMovie::class.java)
        listMovie.let { Log.i("movie", it.toString()) }
    }


    override fun getListData(): List<Detail> {
        val listResult = mutableListOf<Detail>()

        listMovie.results?.let {
            for (item in it) {

                listResult.add(
                    Detail(
                        item.id,
                        item.releaseDate,
                        String.format(formatPoster, item.posterPath),
                        item.title,
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


        listMovie.results?.let {
            for (item in it) {
                if (item.id == id) {
                    itemDetail = Detail(
                        item.id,
                        item.releaseDate,
                        String.format(formatPoster, item.posterPath),
                        item.title,
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