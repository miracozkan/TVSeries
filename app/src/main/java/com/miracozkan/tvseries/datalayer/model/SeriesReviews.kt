package com.miracozkan.tvseries.datalayer.model

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


data class SeriesReviews(

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