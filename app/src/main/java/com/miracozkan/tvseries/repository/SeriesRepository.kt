package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.SeriesReviews
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.utils.Result


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 12:26          │
//└─────────────────────────────┘

interface SeriesRepository {

    fun getSeriesList(): LiveData<Result<List<PopularSeriesResult>?>>

    fun getSeriesVideo(seriesId: Int): LiveData<Result<List<VideoResult>?>>

    fun getSeriesImage(seriesId: Int): LiveData<Result<List<Poster>?>>

    fun getSeriesDetail(seriesId: Int): LiveData<Result<GetSeriesDetail?>>

    fun getSeriesReviews(seriesId: Int): LiveData<Result<List<SeriesReviews>?>>
}