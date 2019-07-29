package com.miracozkan.tvseries.datalayer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 09:59            │
//└─────────────────────────────┘

@Entity(tableName = "series_reviews_table")
data class SeriesReviews(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("author")
        @Expose
        var author: String? = null,
    @SerializedName("content")
        @Expose
        var content: String? = null,
    @SerializedName("id")
        @Expose
        var id: String? = null,
    @SerializedName("url")
        @Expose
        var url: String? = null

)