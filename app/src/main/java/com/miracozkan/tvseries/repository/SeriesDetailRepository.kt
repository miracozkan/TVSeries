package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import com.miracozkan.tvseries.datalayer.model.SeriesReviews
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.utils.Result


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 02.03.2020 - 16:50          │
//└─────────────────────────────┘

interface SeriesDetailRepository {

    fun getSeriesDetail(seriesId: Int): LiveData<Result<GetSeriesDetail>>

    fun getSeriesReview(seriesId: Int): LiveData<Result<List<SeriesReviews>>>

}