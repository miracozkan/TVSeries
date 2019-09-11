package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
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

    val popularSeriesList: MutableLiveData<Resource<List<PopularSeriesResult>>> by lazy { MutableLiveData<Resource<List<PopularSeriesResult>>>() }

    init {
        getPopularSeries()
    }

    private fun getPopularSeries() {
        scope.launch {
            popularSeriesList.postValue(Resource.Loading())
            if (popularSeriesRepository.getPopularSeries().isSuccessful) {
                popularSeriesList.postValue(Resource.Success(popularSeriesRepository.getPopularSeries().body()?.popularSeriesResults))
            } else {
                popularSeriesList.postValue(Resource.Failure(popularSeriesRepository.getPopularSeries().message()))
            }
        }
    }
}