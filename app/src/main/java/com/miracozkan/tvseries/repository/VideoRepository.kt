package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.utils.Result


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 02.03.2020 - 16:36          │
//└─────────────────────────────┘

interface VideoRepository {

    fun getSeriesVideo(seriesId: String): LiveData<Result<List<VideoResult>>>

    fun getSeriesImages(seriesId: String): LiveData<Result<List<Poster>>>
}