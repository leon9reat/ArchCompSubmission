package com.medialink.archcompsubmission.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.medialink.archcompsubmission.R
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.databinding.ActivityDetailBinding
import com.medialink.archcompsubmission.ui.main.fragment.BaseFragment
import com.medialink.archcompsubmission.utils.DataFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var idJenis: Int = 0

    companion object {
        const val EXTRA_JENIS = "extra_jenis"
        const val EXTRA_ID = "extra_id"

        fun showActivity(context: Context?, idJenis: Int, idFilm: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_JENIS, idJenis)
            intent.putExtra(EXTRA_ID, idFilm)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var id = 0

        val bundle = intent.extras
        if (bundle != null) {
            idJenis = bundle.getInt(EXTRA_JENIS, 0)
            id = bundle.getInt(EXTRA_ID, 0)
        }
        val factory = DataFactory.getDetailFactory(idJenis)
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)

        val detail = viewModel.getCurrentData(id)
        setViewData(detail)
    }

    private fun setViewData(detail: Detail) {
        with(binding.detailContent) {
            val lblTanggal = when (idJenis) {
                BaseFragment.PARAM_MOVIE -> resources.getString(
                    R.string.label_release
                )
                else -> resources.getString(R.string.label_airing)
            }

            tvTitleMovieDetail.text = detail.title
            tvLabelDateMovie.text = lblTanggal
            tvReleaseMovie.text = detail.releaseDate

            tvVoteMovie.text = detail.voteAverage.toString()
            progressVoteMovie.progress = detail.voteAverage?.times(10)?.toInt() ?: 0
            tvOverviewMovie.text = detail.overview

            Glide.with(this@DetailActivity)
                .load(detail.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(imgPosterMovieDetail)
        }
    }


}


