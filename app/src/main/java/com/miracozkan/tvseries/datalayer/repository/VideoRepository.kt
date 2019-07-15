package com.miracozkan.tvseries.datalayer.repository

import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.BuildConfig
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.viewmodel.BaseRepository


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 17:51            │
//└─────────────────────────────┘

class VideoRepository(val projectService: ProjectService, private val videoId: String) : BaseRepository() {


    suspend fun getSeriesVideo(): MutableList<VideoResult> {
        return safeApiCall(
            call = { projectService.getSeriesVideoAsync(videoId, BuildConfig.API_KEY).await() },
            error = "Error fetching news"
        )?.results!!.toMutableList()
    }

    suspend fun getSeriesImages(): MutableList<Poster> {
        return safeApiCall(
            call = { projectService.getSeriesImageAsync(videoId, BuildConfig.API_KEY).await() },
            error = "Error fetching news"
        )?.posters!!.toMutableList()
    }
}