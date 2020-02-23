package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
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

}