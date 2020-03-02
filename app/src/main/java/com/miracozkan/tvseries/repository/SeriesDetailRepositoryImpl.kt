package com.miracozkan.tvseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miracozkan.tvseries.BuildConfig.API_KEY
import com.miracozkan.tvseries.datalayer.model.SeriesReviews
import com.miracozkan.tvseries.datalayer.network.ProjectService
import com.miracozkan.tvseries.datalayer.network.response.GetSeriesDetail
import com.miracozkan.tvseries.utils.Result
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 02.03.2020 - 16:50          │
//└─────────────────────────────┘

class SeriesDetailRepositoryImpl @Inject constructor(
    private val projectService: ProjectService
) : CoroutineScope, SeriesDetailRepository {

    private val seriesReviews = MutableLiveData<Result<List<SeriesReviews>>>()
    private val seriesDetail = MutableLiveData<Result<GetSeriesDetail>>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    override fun getSeriesDetail(seriesId: Int): LiveData<Result<GetSeriesDetail>> {
        launch {
            fetchSeriesDetail(seriesId)
        }
        return seriesDetail
    }

    private suspend fun fetchSeriesDetail(seriesId: Int) {
        withContext(Dispatchers.IO) {
            val response = projectService.getSeriesDetailAsync(seriesId, API_KEY)
            if (response.isSuccessful) {
                seriesDetail.postValue(Result.success(response.body()!!))
            } else {
                seriesDetail.postValue(Result.error(response.message()))
            }
        }
    }

    override fun getSeriesReview(seriesId: Int): LiveData<Result<List<SeriesReviews>>> {
        launch {
            fetchSeriesReviews(seriesId)
        }
        return seriesReviews
    }

    private suspend fun fetchSeriesReviews(seriesId: Int) {
        withContext(Dispatchers.IO) {
            val response = projectService.getSeriesReviewsAsync(seriesId, API_KEY)
            if (response.isSuccessful) {
                seriesReviews.postValue(Result.success(response.body()?.results.orEmpty()))
            } else {
                seriesReviews.postValue(Result.error(response.message()))
            }
        }
    }

}