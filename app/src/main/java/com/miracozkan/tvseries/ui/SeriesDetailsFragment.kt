package com.miracozkan.tvseries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.SeriesCastAdapter
import com.miracozkan.tvseries.adapter.SeriesReviewAdapter
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.utils.Resource
import com.miracozkan.tvseries.utils.ViewModelFactory
import com.miracozkan.tvseries.utils.hideProgress
import com.miracozkan.tvseries.viewmodel.SeriesDetailViewModel
import kotlinx.android.synthetic.main.fragment_series_details.*


class SeriesDetailsFragment : Fragment() {
    private const val ARG_PARAM1 = "param1"
    private val seriesDetailRepository by lazy {
        DependencyUtil.getSeriesDetailRepository(
            RetrofitClient.getClient(),
            seriesID = param1!!
        )
    }
    private val seriesDetailViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelFactory(seriesDetailRepository)
        ).get(SeriesDetailViewModel::class.java)
    }

    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_series_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recycCast) {
            adapter = SeriesCastAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        seriesDetailViewModel.seriesDetail.observe(this, Observer { _seriesDetail ->

            when (_seriesDetail) {
                is Resource.Failure -> {
                    Toast.makeText(context, _seriesDetail.cause, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    _seriesDetail.data?.let {
                        if (it.createdBy.isNullOrEmpty()) {
                            txtSeriesDetailCast.append(" -> There is no cast ")
                            recycCast.hideProgress()
                        } else {
                            (recycCast.adapter as SeriesCastAdapter).setNewItem(it.createdBy!!)
                        }
                    }
                }
                is Resource.Loading -> {
                    //TODO may add progress bar
                }
            }
        })

        with(recycReviews) {
            adapter = SeriesReviewAdapter()
            layoutManager = LinearLayoutManager(activity)
        }

        seriesDetailViewModel.seriesReviews.observe(this, Observer { _reviews ->

            when (_reviews) {
                is Resource.Loading -> {
                    //TODO may add progress bar
                }
                is Resource.Success -> {
                    _reviews.data?.let {
                        if (it.isEmpty()) {
                            txtUserReviews.append(" -> There is no comment")
                            recycReviews.hideProgress()
                        } else {

                            (recycReviews.adapter as SeriesReviewAdapter).setNewItem(it)
                        }
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(context, _reviews.cause, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            SeriesDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}
