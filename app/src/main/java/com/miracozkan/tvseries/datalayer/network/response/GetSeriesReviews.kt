package com.miracozkan.tvseries.datalayer.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miracozkan.tvseries.datalayer.model.SeriesReviews


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 09:59            │
//└─────────────────────────────┘

@Entity(tableName = "get_series_reviews_table")
data class GetSeriesReviews(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("id")
        @Expose
        var id: Int? = null,
    @SerializedName("page")
        @Expose
        var page: Int? = null,
    @SerializedName("results")
        @Expose
        var results: List<SeriesReviews>? = null,
    @SerializedName("total_pages")
        @Expose
        var totalPages: Int? = null,
    @SerializedName("total_results")
        @Expose
        var totalResults: Int? = null

)