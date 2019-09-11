package com.miracozkan.tvseries.viewmodelgradle

import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.base.BaseViewModel
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.datalayer.repository.VideoRepository
import com.miracozkan.tvseries.utils.Resource
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

    val seriesVideo: MutableLiveData<Resource<List<VideoResult>>> by lazy { MutableLiveData<Resource<List<VideoResult>>>() }
    val seriesImage: MutableLiveData<Resource<List<Poster>>> by lazy { MutableLiveData<Resource<List<Poster>>>() }

    init {
        getVideos()
        getImages()
    }

    private fun getVideos() {
        scope.launch {
            seriesVideo.postValue(Resource.Loading())
            if (videoRepository.getSeriesVideo().isSuccessful) {
                seriesVideo.postValue(Resource.Success(videoRepository.getSeriesVideo().body()?.results))
            } else {
                seriesVideo.postValue(Resource.Failure(videoRepository.getSeriesVideo().message()))
            }
        }
    }

    private fun getImages() {
        scope.launch {
            seriesImage.postValue(Resource.Loading())
            if (videoRepository.getSeriesVideo().isSuccessful) {
                seriesImage.postValue(Resource.Success(videoRepository.getSeriesImages().body()?.posters))
            } else {
                seriesImage.postValue(Resource.Failure(videoRepository.getSeriesImages().message()))
            }
        }
    }
}