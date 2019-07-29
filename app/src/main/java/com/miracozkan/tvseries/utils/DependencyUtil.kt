package com.miracozkan.tvseries.utils

import com.miracozkan.tvseries.datalayer.localdb.ProjectDao
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
import com.miracozkan.tvseries.datalayer.repository.SeriesDetailRepository
import com.miracozkan.tvseries.datalayer.repository.VideoRepository


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 01/07/19 - 17:39            │
//└─────────────────────────────┘

object DependencyUtil {

    fun getPopularSeriesRepository(projectService: ProjectService): PopularSeriesRepository =
        PopularSeriesRepository(projectService = projectService)

    fun getVideoRepository(projectService: ProjectService, videoID: String): VideoRepository =
        VideoRepository(projectService = projectService, videoId = videoID)

    fun getSeriesDetailRepository(
        projectService: ProjectService,
        seriesID: Int,
        projectDao: ProjectDao
    ): SeriesDetailRepository =
        SeriesDetailRepository(projectService = projectService, seriesID = seriesID, projectDao = projectDao)

}