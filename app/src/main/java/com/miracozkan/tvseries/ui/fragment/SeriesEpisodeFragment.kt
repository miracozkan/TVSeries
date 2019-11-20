package com.miracozkan.tvseries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.SeriesSeasonsAdapter
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.*
import com.miracozkan.tvseries.viewmodel.SeriesDetailViewModel
import kotlinx.android.synthetic.main.fragment_series_episode.*


class SeriesEpisodeFragment : BaseFragment() {

    private var param1: Int? = null

    private val seriesDetailRepository by lazy {
        DependencyUtil.getSeriesDetailRepository(
            RetrofitClient.getClient(), seriesID = param1!!
        )
    }
    private val seriesDetailViewModel by lazy {
        ViewModelProviders.of(
            activity!!,
            ViewModelFactory(seriesDetailRepository)
        ).get(SeriesDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(seriesID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_series_episode, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
        initObserver()
    }

    private fun initUi() {
        with(recycSeasons) {
            adapter = SeriesSeasonsAdapter()
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun initObserver() {
        seriesDetailViewModel.seriesDetail.observe(this, Observer { _it ->
            when (_it.status) {
                Status.LOADING -> {
                    //TODO may add progress bar
                }
                Status.SUCCESS -> {
                    _it.data?.let {
                        if (it.seasons.isNullOrEmpty()) {
                            txtNoSeason.text = "There is no season"
                            recycSeasons.hideProgress()
                            txtNoSeason.showProgress()
                        } else {
                            (recycSeasons.adapter as SeriesSeasonsAdapter).setNewItem(it.seasons!!)
                        }
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(context, _it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            SeriesEpisodeFragment().apply {
                arguments = Bundle().apply {
                    putInt(seriesID, param1)
                }
            }

        private const val seriesID = "seriesID"
    }
}
