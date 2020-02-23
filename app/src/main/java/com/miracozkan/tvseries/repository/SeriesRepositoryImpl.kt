package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.BuildConfig.API_KEY
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
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


    private val seriesListResponse = MutableLiveData<Result<List<PopularSeriesResult>?>>()

    private val seriesVideoResult = MutableLiveData<Result<List<VideoResult>?>>()

    private val seriesImagesResult = MutableLiveData<Result<List<Poster>?>>()

    override fun getSeriesList(): LiveData<Result<List<PopularSeriesResult>?>> {
        launch {
            fetchSeriesList()
        }
        return seriesListResponse
    }

    override fun getSeriesVideo(seriesId: Int): LiveData<Result<List<VideoResult>?>> {
        launch {
            fetchSeriesVideo(seriesId)
        }
        return seriesVideoResult
    }

    override fun getSeriesImage(seriesId: Int): LiveData<Result<List<Poster>?>> {
        launch {
            fetchSeriesImage(seriesId)
        }
        return seriesImagesResult
    }

    private suspend fun fetchSeriesImage(seriesId: Int) {
        withContext(Dispatchers.IO) {
            try {
                seriesImagesResult.postValue(Result.loading())
                val response = projectService
                    .getSeriesImageAsync(seriesId.toString(), API_KEY)
                if (response.isSuccessful) {
                    seriesImagesResult.postValue(Result.success(response.body()!!.posters))
                } else {
                    seriesImagesResult.postValue(Result.error(response.message()))
                }
            } catch (e: IOException) {
                seriesImagesResult.postValue(Result.error(e.localizedMessage!!))
            }
        }
    }

    private suspend fun fetchSeriesVideo(seriesId: Int) {
        withContext(Dispatchers.IO) {
            try {
                seriesVideoResult.postValue(Result.loading())
                val response = projectService
                    .getSeriesVideoAsync(seriesId.toString(), API_KEY)
                if (response.isSuccessful) {
                    seriesVideoResult.postValue(Result.success(response.body()!!.results))
                } else {
                    seriesVideoResult.postValue(Result.error(response.message()))
                }
            } catch (e: IOException) {
                seriesVideoResult.postValue(Result.error(e.localizedMessage!!))
            }
        }
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