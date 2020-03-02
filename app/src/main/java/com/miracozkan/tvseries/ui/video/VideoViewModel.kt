package com.miracozkan.tvseries.ui.video

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
//│ 13/07/19 - 17:50            │
//└─────────────────────────────┘

class VideoViewModel @Inject constructor(private val seriesRepository: SeriesRepository) :
    BaseViewModel() {

    private val _seriesID = MutableLiveData<String>()
    val seriesVideo = _seriesID.switchMap {
        seriesRepository.getSeriesVideo(it.toInt())
    }
    val seriesImages = _seriesID.switchMap {
        seriesRepository.getSeriesImage(it.toInt())
    }

    fun setSeriesId(seriesId: String) {
        _seriesID.postValue(seriesId)
    }
}