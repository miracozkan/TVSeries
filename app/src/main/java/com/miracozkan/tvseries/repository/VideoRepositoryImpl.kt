package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.BuildConfig.API_KEY
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.utils.Result
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 02.03.2020 - 16:36          │
//└─────────────────────────────┘

class VideoRepositoryImpl @Inject constructor(private val projectService: ProjectService) :
    CoroutineScope, VideoRepository {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val seriesVideo = MutableLiveData<Result<List<VideoResult>>>()
    private val seriesImage = MutableLiveData<Result<List<Poster>>>()

    override fun getSeriesVideo(seriesId: String): LiveData<Result<List<VideoResult>>> {
        launch {
            fetchSeriesVideo(seriesId)
        }
        return seriesVideo
    }

    private suspend fun fetchSeriesVideo(seriesId: String) {
        withContext(Dispatchers.IO) {
            val response = projectService.getSeriesVideoAsync(seriesId, API_KEY)
            if (response.isSuccessful) {
                seriesVideo.postValue(Result.success(response.body()?.results.orEmpty()))
            } else {
                seriesVideo.postValue(Result.error(response.message()))
            }
        }
    }

    override fun getSeriesImages(seriesId: String): LiveData<Result<List<Poster>>> {
        launch {
            fetchSeriesImages(seriesId)
        }
        return seriesImage
    }

    private suspend fun fetchSeriesImages(seriesId: String) {
        withContext(Dispatchers.IO) {
            val response = projectService.getSeriesImageAsync(seriesId, API_KEY)
            if (response.isSuccessful) {
                seriesImage.postValue(Result.success(response.body()?.posters.orEmpty()))
            } else {
                seriesImage.postValue(Result.error(response.message()))
            }
        }
    }

}