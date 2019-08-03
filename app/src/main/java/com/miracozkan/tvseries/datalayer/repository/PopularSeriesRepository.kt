package com.miracozkan.tvseries.datalayer.repository

import com.miracozkan.tvseries.BuildConfig.API_KEY
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.utils.BaseRepository


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 16:48            │
//└─────────────────────────────┘

class PopularSeriesRepository(private val projectService: ProjectService) :
        BaseRepository() {

    suspend fun getPopularSeries(): MutableList<PopularSeriesResult>? {
        return safeApiCall(
            call = { projectService.getPopularSeriesAsync(API_KEY).await() },
            error = "Error fetching news"
        )?.popularSeriesResults?.toMutableList()
    }
}