package com.miracozkan.tvseries.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 01/07/19 - 17:39            │
//└─────────────────────────────┘

class PopularSeriesViewModel(private val popularSeriesRepository: PopularSeriesRepository) : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    val popularSeriesList: MutableLiveData<List<PopularSeriesResult>> by lazy { MutableLiveData<List<PopularSeriesResult>>() }

    init {
        getPopularSeries()
    }

    private fun getPopularSeries() {
        scope.launch {
            popularSeriesList.postValue(popularSeriesRepository.getPopularSeries())
        }
    }

    fun cancelRequests() = coroutineContext.cancel()

}