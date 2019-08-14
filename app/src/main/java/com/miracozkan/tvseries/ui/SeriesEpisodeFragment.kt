package com.miracozkan.tvseries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.SeriesSeasonsAdapter
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.utils.ViewModelFactory
import com.miracozkan.tvseries.viewmodel.SeriesDetailViewModel
import kotlinx.android.synthetic.main.fragment_series_episode.*

private const val seriesID = "seriesID"

class SeriesEpisodeFragment : Fragment() {
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recycSeasons) {
            adapter = SeriesSeasonsAdapter()
            layoutManager = LinearLayoutManager(activity)
        }

        seriesDetailViewModel.seriesDetail.observe(this, Observer { _it ->
            if (!(_it.seasons).isNullOrEmpty()) {

                (recycSeasons.adapter as SeriesSeasonsAdapter).setNewItem(_it.seasons!!)
            } else {
                txtNoSeason.text = "There is no season"
                recycSeasons.visibility = View.GONE
                txtNoSeason.visibility = View.VISIBLE
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
    }
}
