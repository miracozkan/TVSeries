package com.miracozkan.tvseries.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.repository.SeriesRepository
import javax.inject.Inject


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 10:06            │
//└─────────────────────────────┘

class SeriesDetailViewModel @Inject constructor(private val seriesRepository: SeriesRepository) :
    BaseViewModel() {

    private val _seriesId = MutableLiveData<Int>()

    val seriesDetail = _seriesId.switchMap {
        seriesRepository.getSeriesDetail(it)
    }

    val seriesReviews = _seriesId.switchMap {
        seriesRepository.getSeriesReviews(it)
    }

    fun setSeriesId(seriesId: Int) {
        _seriesId.postValue(seriesId)
    }

}