package com.miracozkan.tvseries.datalayer.network

import com.miracozkan.tvseries.datalayer.network.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
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
    suspend fun getPopularSeriesAsync(
        @Query("api_key") apiKey: String
    ): Response<GetPopular>

    @GET("tv/{seriesID}/videos")
    suspend fun getSeriesVideoAsync(
        @Path("seriesID") seriesID: String,
        @Query("api_key") apiKey: String
    ): Response<GetVideo>

    @GET("tv/{seriesID}/images")
    suspend fun getSeriesImageAsync(
        @Path("seriesID") seriesID: String,
        @Query("api_key") apiKey: String
    ): Response<GetImages>

    @Headers("Access-Control-Expose-Headers: Retry-After")
    @GET("tv/{seriesID}/reviews")
    suspend fun getSeriesReviewsAsync(
        @Path("seriesID") seriesID: Int,
        @Query("api_key") apiKey: String
    ): Response<GetSeriesReviews>

    @GET("tv/{seriesID}")
    suspend fun getSeriesDetailAsync(
        @Path("seriesID") seriesID: Int,
        @Query("api_key") apiKey: String
    ): Response<GetSeriesDetail>
}