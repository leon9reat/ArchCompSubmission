package com.medialink.archcompsubmission.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.medialink.archcompsubmission.data.repository.IRepository
import com.medialink.archcompsubmission.data.repository.MoviesRepository
import com.medialink.archcompsubmission.data.repository.TvShowsRepository
import com.medialink.archcompsubmission.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var repository: IRepository

    private var idJenis: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idJenis = it.getInt(PARAMETER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            repository = when (idJenis) {
                PARAM_MOVIE -> MoviesRepository()
                else -> TvShowsRepository()
            }

            val factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return  MovieViewModel(repository) as T
                }
            }

            val viewModel = ViewModelProvider(this,factory).get(MovieViewModel::class.java)
            val listData = viewModel.getData()
            val movieAdapter = idJenis?.let { MovieAdapter(it) }
            movieAdapter?.setData(listData)

            with(fragmentMovieBinding.moviesRv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    companion object {
        val PARAMETER: String = "PARAMETER"
        const val PARAM_MOVIE = 0
        const val PARAM_TV_SHOW = 1

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment MovieFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAMETER, param1)
                }
            }
    }
}