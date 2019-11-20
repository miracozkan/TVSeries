package com.miracozkan.tvseries.datalayer.repository

import com.miracozkan.tvseries.BuildConfig
import com.miracozkan.tvseries.base.BaseRepository
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesReviews
import com.miracozkan.tvseries.utils.Result


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 10:01            │
//└─────────────────────────────┘

class SeriesDetailRepository(
    private val projectService: ProjectService,
    private val seriesID: Int
) : BaseRepository() {

    suspend fun getSeriesDetail(): Result<GetSeriesDetail> {
        return getResult {
            projectService.getSeriesDetailAsync(seriesID, BuildConfig.API_KEY)
        }
    }

    suspend fun getSeriesReview(): Result<GetSeriesReviews> {
        return getResult {
            projectService.getSeriesReviewsAsync(seriesID, BuildConfig.API_KEY)
        }
    }
}