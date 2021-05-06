package com.medialink.archcompsubmission.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.medialink.archcompsubmission.R
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.databinding.MovieItemBinding

class MovieAdapter(var mJenis: Int) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var mListData = ArrayList<Detail>()

    fun setData(listData: List<Detail>?) {
        if (listData == null) return
        this.mListData.clear()
        this.mListData.addAll(listData)

    }

    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(detail: Detail) {
            val lblTanggal = when (mJenis) {
                MovieFragment.PARAM_MOVIE -> itemView.resources.getString(R.string.label_release, detail.releaseDate)
                else -> itemView.resources.getString(R.string.label_airing, detail.releaseDate)
            }
            with(binding) {
                tvTitleMovieList.text = detail.title
                tvDateMovie.text = lblTanggal
                tvVoteMovie.text = detail.voteAverage.toString()
                progressVoteMovie.progress = detail.voteAverage?.times(10)?.toInt() ?: 0
                tvOverviewMovie.text = detail.overview

                Glide.with(itemView.context)
                    .load(detail.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPosterMovieList)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieItemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val course = mListData[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = mListData.size
}