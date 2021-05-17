package com.medialink.archcompsubmission.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.medialink.archcompsubmission.data.model.Detail
import com.medialink.archcompsubmission.databinding.FragmentBaseBinding
import com.medialink.archcompsubmission.ui.detail.DetailActivity
import com.medialink.archcompsubmission.utils.DataFactory


class BaseFragment : Fragment(), BaseFragmentCallback {
    private lateinit var fragmentBaseBinding: FragmentBaseBinding
    private var idJenis: Int = 0

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
        fragmentBaseBinding = FragmentBaseBinding.inflate(layoutInflater, container, false)
        return fragmentBaseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            //val jsonFileString = getJsonDataFromAsset(requireActivity().applicationContext, "list_movie.json")
            /*val gson = Gson()
            val jsonString = getJsonDataFromAsset(requireActivity().applicationContext, "list_movie.json")
            val jsonFileString: ListMovie = gson.fromJson(jsonString, ListMovie::class.java)
            jsonFileString.let { Log.i("data", it.toString()) }*/


            val factory = DataFactory.getFactory(requireActivity().applicationContext, idJenis)
            val viewModel = ViewModelProvider(this, factory).get(BaseViewModel::class.java)
            val listData = viewModel.getListData()
            val movieAdapter = BaseAdapter(idJenis, this)
            movieAdapter.setData(listData)

            with(fragmentBaseBinding.moviesRv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            fragmentBaseBinding.moviesSwipe.setOnRefreshListener {
                Toast.makeText(context, "Refresh data here", Toast.LENGTH_SHORT).show()
                stopRefreshing()
            }
        }
    }

    private fun stopRefreshing() {
        if (fragmentBaseBinding.moviesSwipe.isRefreshing) {
            fragmentBaseBinding.moviesSwipe.isRefreshing = false
        }
    }

    companion object {
        const val PARAMETER: String = "PARAMETER"
        const val PARAM_MOVIE = 0
        const val PARAM_TV_SHOW = 1

        @JvmStatic
        fun newInstance(param1: Int) =
            BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAMETER, param1)
                }
            }
    }

    override fun onItemClick(detail: Detail) {

        val data = detail.id.toString() + " " + idJenis.toString()
        detail.id?.let { DetailActivity.showActivity(this.context, idJenis, it) }
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
    }

    override fun onShareClick(detail: Detail) {
        Snackbar.make(
            fragmentBaseBinding.myCoordinatorLayout,
            "Share",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onFavoriteClick(detail: Detail) {
        Snackbar.make(
            fragmentBaseBinding.myCoordinatorLayout,
            "Favorite",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}