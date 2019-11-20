package com.miracozkan.tvseries.datalayer.repository

import com.miracozkan.tvseries.BuildConfig.API_KEY
import com.miracozkan.tvseries.base.BaseRepository
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.datalayer.network.response.GetPopular
import com.miracozkan.tvseries.utils.Result


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

    suspend fun getPopularSeries(): Result<GetPopular> {
        return getResult { projectService.getPopularSeriesAsync(API_KEY) }
    }
}