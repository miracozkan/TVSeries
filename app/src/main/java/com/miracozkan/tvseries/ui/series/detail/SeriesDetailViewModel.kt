package com.miracozkan.tvseries.ui.series.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.miracozkan.tvseries.repository.SeriesRepository
import javax.inject.Inject


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 24.02.2020 - 20:00          │
//└─────────────────────────────┘

class SeriesDetailViewModel @Inject constructor(seriesRepository: SeriesRepository) : ViewModel() {

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