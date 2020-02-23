package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.BuildConfig.API_KEY
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.utils.Result
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 12:26          │
//└─────────────────────────────┘

class SeriesRepositoryImpl @Inject constructor(private val projectService: ProjectService) :
    SeriesRepository, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    private val seriesListResponse = MutableLiveData<Result<List<PopularSeriesResult?>>>()

    override fun getSeriesList(): LiveData<Result<List<PopularSeriesResult?>>> {
        launch {
            fetchSeriesList()
        }
        return seriesListResponse
    }

    private suspend fun fetchSeriesList() {
        withContext(Dispatchers.IO) {
            try {
                seriesListResponse.postValue(Result.loading())
                val response = projectService.getPopularSeriesAsync(API_KEY)
                if (response.isSuccessful) {
                    seriesListResponse.postValue(Result.success(response.body()!!.popularSeriesResults))
                } else {
                    seriesListResponse.postValue(Result.error(response.message()))
                }
            } catch (e: IOException) {
                seriesListResponse.postValue(Result.error(e.localizedMessage!!))
            }
        }
    }
}