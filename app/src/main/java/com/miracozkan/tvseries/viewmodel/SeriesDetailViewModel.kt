package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.datalayer.model.SeriesReviews
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.datalayer.repository.SeriesDetailRepository
import com.miracozkan.tvseries.utils.Result
import com.miracozkan.tvseries.utils.Status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 10:06            │
//└─────────────────────────────┘

class SeriesDetailViewModel(private val seriesDetailRepository: SeriesDetailRepository) :
    BaseViewModel() {

    private val _seriesReviews by lazy { MutableLiveData<Result<List<SeriesReviews>>>() }
    private val _seriesDetail by lazy { MutableLiveData<Result<GetSeriesDetail>>() }

    val seriesReviews: MutableLiveData<Result<List<SeriesReviews>>>
        get() = _seriesReviews

    val seriesDetail: MutableLiveData<Result<GetSeriesDetail>>
        get() = _seriesDetail

    private var detailJob: Job? = null
    private var reviewsJob: Job? = null

    init {
        setReviewsJob()
        setDetailJob()
    }

    private fun setReviewsJob() {
        if (reviewsJob?.isActive == true) {
            return
        }
        reviewsJob = fetchReviews()
    }

    private fun fetchReviews(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            when (seriesDetailRepository.getSeriesReview().status) {
                SUCCESS -> {
                    _seriesReviews.postValue(Result.success(seriesDetailRepository.getSeriesReview().data?.results.orEmpty()))
                }
                ERROR -> {
                    _seriesReviews.postValue(
                        Result.error(
                            seriesDetailRepository.getSeriesReview().message
                                ?: "SeriesDetailViewModel Error!!"
                        )
                    )
                }
                LOADING -> {
                    _seriesReviews.postValue(Result.loading())
                }
            }
        }
    }

    private fun setDetailJob() {
        if (detailJob?.isActive == true) {
            return
        }
        detailJob = fetchDetail()
    }

    private fun fetchDetail(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            when (seriesDetailRepository.getSeriesDetail().status) {
                SUCCESS -> {
                    _seriesDetail.postValue(
                        Result.success(
                            seriesDetailRepository.getSeriesDetail().data ?: GetSeriesDetail()
                        )
                    )
                }
                ERROR -> {
                    _seriesDetail.postValue(
                        Result.error(
                            seriesDetailRepository.getSeriesDetail().message
                                ?: "SeriesDetailViewModel Error!!"
                        )
                    )
                }
                LOADING -> {
                    _seriesDetail.postValue(Result.loading())
                }
            }
        }
    }
}