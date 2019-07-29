package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.datalayer.repository.VideoRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 17:50            │
//└─────────────────────────────┘

class VideoViewModel(private val videoRepository: VideoRepository) : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    val seriesVideo: MutableLiveData<List<VideoResult>> by lazy { MutableLiveData<List<VideoResult>>() }
    val seriesImage: MutableLiveData<List<Poster>> by lazy { MutableLiveData<List<Poster>>() }

    init {
        getVideos()
        getImages()
    }

    private fun getVideos() {
        scope.launch {
            seriesVideo.postValue(videoRepository.getSeriesVideo())
        }
    }

    private fun getImages() {
        scope.launch {
            seriesImage.postValue(videoRepository.getSeriesImages())
        }
    }

    fun cancelRequests() = coroutineContext.cancel()

}