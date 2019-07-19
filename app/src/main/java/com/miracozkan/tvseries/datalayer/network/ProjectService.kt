package com.miracozkan.tvseries.datalayer.network

import com.miracozkan.tvseries.datalayer.network.response.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 16:41            │
//└─────────────────────────────┘

interface ProjectService {

    @GET("tv/popular")
    fun getPopularSeriesAsync(
        @Query("api_key") apiKey: String
    ): Deferred<Response<GetPopular>>

    @GET("tv/{seriesID}/videos")
    fun getSeriesVideoAsync(
        @Path("seriesID") seriesID: String,
        @Query("api_key") apiKey: String
    ): Deferred<Response<GetVideo>>

    @GET("tv/{seriesID}/images")
    fun getSeriesImageAsync(
        @Path("seriesID") seriesID: String,
        @Query("api_key") apiKey: String
    ): Deferred<Response<GetImages>>

    @GET("tv/{seriesID}/reviews")
    fun getSeriesReviewsAsync(
            @Path("seriesID") seriesID: Int,
            @Query("api_key") apiKey: String
    ): Deferred<Response<GetSeriesReviews>>

    @GET("tv/{seriesID}")
    fun getSeriesDetailAsync(
            @Path("seriesID") seriesID: Int,
            @Query("api_key") apiKey: String
    ): Deferred<Response<GetSeriesDetail>>
}