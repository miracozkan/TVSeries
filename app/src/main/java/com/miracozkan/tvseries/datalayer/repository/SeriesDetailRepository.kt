package com.miracozkan.tvseries.datalayer.repository

import com.miracozkan.tvseries.BuildConfig
import com.miracozkan.tvseries.datalayer.localdb.ProjectDao
import com.miracozkan.tvseries.datalayer.model.SeriesReviews
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.utils.BaseRepository


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
    private val seriesID: Int,
    private val projectDao: ProjectDao
) : BaseRepository() {

    suspend fun getSeriesReview(): MutableList<SeriesReviews> {
        return safeApiCall(
            call = { projectService.getSeriesReviewsAsync(seriesID, BuildConfig.API_KEY).await() },
            error = "Error fetching news"
        )?.results!!.toMutableList()
    }

    suspend fun getSeriesDetail(): GetSeriesDetail {
        return safeApiCall(
            call = { projectService.getSeriesDetailAsync(seriesID, BuildConfig.API_KEY).await() },
            error = "Error fetching news"
        )!!
    }

    suspend fun insert(getSeriesDetail: GetSeriesDetail) {
        projectDao.insertSeriesDetail(getSeriesDetail)
    }

    suspend fun getAllData(): GetSeriesDetail {
        return projectDao.getSeriesDetail(seriesID)
    }

}