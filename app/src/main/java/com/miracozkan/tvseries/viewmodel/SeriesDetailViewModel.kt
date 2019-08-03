package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miracozkan.tvseries.datalayer.model.SeriesReviews
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.datalayer.repository.SeriesDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 10:06            │
//└─────────────────────────────┘

class SeriesDetailViewModel(private val seriesDetailRepository: SeriesDetailRepository) : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    val seriesReviews: MutableLiveData<List<SeriesReviews>> by lazy { MutableLiveData<List<SeriesReviews>>() }
    val seriesDetail: MutableLiveData<GetSeriesDetail> by lazy { MutableLiveData<GetSeriesDetail>() }

    init {
        getSeriesReviews()
        getSeriesDetail()
    }

    private fun getSeriesReviews() {
        scope.launch {
            seriesReviews.postValue(seriesDetailRepository.getSeriesReview())
        }
    }

    private fun getSeriesDetail() {
        scope.launch {
            seriesDetail.postValue(seriesDetailRepository.getSeriesDetail())
        }
    }
}