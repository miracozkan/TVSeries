package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.datalayer.repository.VideoRepository
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
//│ 13/07/19 - 17:50            │
//└─────────────────────────────┘

class VideoViewModel(private val videoRepository: VideoRepository) : BaseViewModel() {

    val seriesVideo: MutableLiveData<Result<List<VideoResult>>> by lazy { MutableLiveData<Result<List<VideoResult>>>() }
    val seriesImage: MutableLiveData<Result<List<Poster>>> by lazy { MutableLiveData<Result<List<Poster>>>() }

    private var videoJob: Job? = null
    private var imageJob: Job? = null

    init {
        setVideosJob()
        setImageJob()
    }

    private fun setVideosJob() {
        if (videoJob?.isActive == true) {
            return
        }
        videoJob = fetchVideos()
    }

    private fun fetchVideos(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            when (videoRepository.getSeriesVideo().status) {
                SUCCESS -> {
                    seriesVideo.postValue(Result.success((videoRepository.getSeriesVideo().data?.results).orEmpty()))
                }
                ERROR -> {
                    seriesVideo.postValue(
                        Result.error(
                            videoRepository.getSeriesVideo().message ?: "VideoViewModel Error!!"
                        )
                    )
                }
                LOADING -> {
                    seriesVideo.postValue(Result.loading())
                }
            }
        }
    }

    private fun setImageJob() {
        if (imageJob?.isActive == true) {
            return
        }
        imageJob = fetchImage()
    }

    private fun fetchImage(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            when (videoRepository.getSeriesImages().status) {
                SUCCESS -> {
                    seriesImage.postValue(Result.success(videoRepository.getSeriesImages().data?.posters.orEmpty()))
                }
                ERROR -> {
                    seriesImage.postValue(
                        Result.error(
                            videoRepository.getSeriesImages().message ?: "VideoViewModel Error!!"
                        )
                    )
                }
                LOADING -> {
                    seriesImage.postValue(Result.loading())
                }
            }
        }
    }
}