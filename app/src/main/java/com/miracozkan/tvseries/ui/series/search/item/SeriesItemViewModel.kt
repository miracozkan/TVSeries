package com.miracozkan.tvseries.ui.series.search.item

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
//│ 23.02.2020 - 12:16          │
//└─────────────────────────────┘

class SeriesItemViewModel @Inject constructor(seriesRepository: SeriesRepository) : ViewModel() {

    private val seriesId = MutableLiveData<Int>()

    val seriesVideo = seriesId.switchMap {
        seriesRepository.getSeriesVideo(it)
    }

    val seriesImages = seriesId.switchMap {
        seriesRepository.getSeriesImage(it)
    }

    fun setSeriesId(seriesId: Int) {
        this.seriesId.postValue(seriesId)
    }

}