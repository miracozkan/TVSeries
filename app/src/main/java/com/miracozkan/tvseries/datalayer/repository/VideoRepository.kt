package com.miracozkan.tvseries.datalayer.repository

import com.miracozkan.tvseries.BuildConfig
import com.miracozkan.tvseries.base.BaseRepository
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.datalayer.network.response.GetImages
import com.miracozkan.tvseries.datalayer.network.response.GetVideo
import retrofit2.Response


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 17:51            │
//└─────────────────────────────┘

class VideoRepository(val projectService: ProjectService, private val videoId: String) :
    BaseRepository() {

    suspend fun getSeriesVideo(): Response<GetVideo> {
        return projectService.getSeriesVideoAsync(videoId, BuildConfig.API_KEY).await()
    }

    suspend fun getSeriesImages(): Response<GetImages> {
        return projectService.getSeriesImageAsync(videoId, BuildConfig.API_KEY).await()
    }
}