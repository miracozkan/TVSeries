package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.datalayer.model.SeriesReviews
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.datalayer.repository.SeriesDetailRepository
import com.miracozkan.tvseries.utils.Resource
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

    private val _seriesReviews by lazy { MutableLiveData<Resource<List<SeriesReviews>>>() }
    private val _seriesDetail by lazy { MutableLiveData<Resource<GetSeriesDetail>>() }

    val seriesReviews: MutableLiveData<Resource<List<SeriesReviews>>>
        get() = _seriesReviews

    val seriesDetail: MutableLiveData<Resource<GetSeriesDetail>>
        get() = _seriesDetail

    init {
        getSeriesReviews()
        getSeriesDetail()
    }

    private fun getSeriesReviews() {
        scope.launch {
            seriesReviews.postValue(Resource.Loading())
            if (!seriesDetailRepository.getSeriesReview().isNullOrEmpty()) {
                seriesReviews.postValue(Resource.Success(seriesDetailRepository.getSeriesReview()))
            } else {
                seriesReviews.postValue(Resource.Failure("Something went wrong!!"))
            }
        }
    }

    private fun getSeriesDetail() {
        scope.launch {
            seriesDetail.postValue(Resource.Loading())
            if (seriesDetailRepository.getSeriesDetail().id != null) {
                seriesDetail.postValue(Resource.Success(seriesDetailRepository.getSeriesDetail()))
            } else {
                seriesDetail.postValue(Resource.Failure("Something Went Wrong!!"))
            }
        }
    }
}