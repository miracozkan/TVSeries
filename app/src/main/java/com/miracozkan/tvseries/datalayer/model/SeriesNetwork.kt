package com.miracozkan.tvseries.datalayer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 10:17            │
//└─────────────────────────────┘

data class SeriesNetwork(

        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("logo_path")
        @Expose
        var logoPath: String? = null,
        @SerializedName("origin_country")
        @Expose
        var originCountry: String? = null

)