package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
import kotlinx.coroutines.launch


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 01/07/19 - 17:39            │
//└─────────────────────────────┘

class PopularSeriesViewModel(private val popularSeriesRepository: PopularSeriesRepository) :
    BaseViewModel() {

    val popularSeriesList: MutableLiveData<List<PopularSeriesResult>> by lazy { MutableLiveData<List<PopularSeriesResult>>() }

    init {
        getPopularSeries()
    }

    private fun getPopularSeries() {
        scope.launch {
            popularSeriesList.postValue(popularSeriesRepository.getPopularSeries())
        }
    }
}