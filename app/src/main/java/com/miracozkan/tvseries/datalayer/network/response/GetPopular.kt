package com.miracozkan.tvseries.datalayer.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 16:38            │
//└─────────────────────────────┘

@Entity(tableName = "get_popular_table")
data class GetPopular(
    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("results")
    @Expose
    var popularSeriesResults: List<PopularSeriesResult>? = null
)