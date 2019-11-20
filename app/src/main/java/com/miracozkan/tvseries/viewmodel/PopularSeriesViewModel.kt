package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
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
//│ 01/07/19 - 17:39            │
//└─────────────────────────────┘

class PopularSeriesViewModel(private val popularSeriesRepository: PopularSeriesRepository) :
    BaseViewModel() {

    private val _popularSeriesList: MutableLiveData<Result<List<PopularSeriesResult>>>
            by lazy { MutableLiveData<Result<List<PopularSeriesResult>>>() }

    val popularSeriesList: MutableLiveData<Result<List<PopularSeriesResult>>>
        get() = _popularSeriesList

    private var popularSeriesJob: Job? = null

    init {
        setPopularSeriesJob()
    }

    private fun setPopularSeriesJob() {
        if (popularSeriesJob?.isActive == true) {
            return
        }
        popularSeriesJob = fetchPopularSeries()
    }

    private fun fetchPopularSeries(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            when (popularSeriesRepository.getPopularSeries().status) {
                SUCCESS -> {
                    _popularSeriesList.postValue(Result.success(popularSeriesRepository.getPopularSeries().data?.popularSeriesResults.orEmpty()))
                }
                ERROR -> {
                    _popularSeriesList.postValue(
                        Result.error(
                            popularSeriesRepository.getPopularSeries().message
                                ?: "PopularSeriesViewModel Error!!"
                        )
                    )
                }
                LOADING -> {
                    _popularSeriesList.postValue(Result.loading())
                }
            }
        }
    }
}