package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
import com.miracozkan.tvseries.utils.Event
import com.miracozkan.tvseries.utils.Resource
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

    private val _popularSeriesList: MutableLiveData<Resource<Event<List<PopularSeriesResult>>>>
            by lazy { MutableLiveData<Resource<Event<List<PopularSeriesResult>>>>() }

    val popularSeriesList: MutableLiveData<Resource<Event<List<PopularSeriesResult>>>>
        get() = _popularSeriesList

    init {
        getPopularSeries()
    }

    private fun getPopularSeries() {
        scope.launch {
            _popularSeriesList.postValue(Resource.Loading())
            if (popularSeriesRepository.getPopularSeries().isSuccessful) {
                _popularSeriesList.postValue(
                    Resource.Success(Event(popularSeriesRepository.getPopularSeries().body()?.popularSeriesResults!!))
                )
            } else {
                _popularSeriesList.postValue(Resource.Failure(popularSeriesRepository.getPopularSeries().message()))
            }
        }
    }
}