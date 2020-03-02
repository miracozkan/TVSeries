package com.miracozkan.tvseries.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.SeriesCastAdapter
import com.miracozkan.tvseries.adapter.SeriesReviewAdapter
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.utils.Status
import com.miracozkan.tvseries.utils.extensions.injectViewModel
import com.miracozkan.tvseries.utils.hideProgress
import kotlinx.android.synthetic.main.fragment_series_details.*


class SeriesDetailsFragment : BaseFragment() {

    private lateinit var seriesDetailViewModel: SeriesDetailViewModel
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        seriesDetailViewModel = injectViewModel(viewModelFactory)
        seriesDetailViewModel.setSeriesId(param1 ?: 1)
        initUi()
        initDetailObserver()
        initReviewsObserver()
    }

    private fun initUi() {
        with(recycCast) {
            adapter = SeriesCastAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        with(recycReviews) {
            adapter = SeriesReviewAdapter()
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun initReviewsObserver() {
        seriesDetailViewModel.seriesReviews.observe(viewLifecycleOwner, Observer { _reviews ->

            when (_reviews.status) {
                Status.LOADING -> {
                    //TODO may add progress bar
                }
                Status.SUCCESS -> {
                    _reviews.data?.let {
                        if (it.isEmpty()) {
                            txtUserReviews.append(" -> There is no comment")
                            recycReviews.hideProgress()
                        } else {

                            (recycReviews.adapter as SeriesReviewAdapter).setNewItem(it)
                        }
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(context, _reviews.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initDetailObserver() {
        seriesDetailViewModel.seriesDetail.observe(viewLifecycleOwner, Observer { _seriesDetail ->

            when (_seriesDetail.status) {
                Status.ERROR -> {
                    Toast.makeText(context, _seriesDetail.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    _seriesDetail.data?.let {
                        if (it.createdBy.isNullOrEmpty()) {
                            txtSeriesDetailCast.append(" -> There is no cast ")
                            recycCast.hideProgress()
                        } else {
                            (recycCast.adapter as SeriesCastAdapter).setNewItem(it.createdBy!!)
                        }
                    }
                }
                Status.LOADING -> {
                    //TODO may add progress bar
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

        private const val ARG_PARAM1 = "param1"
    }
}
