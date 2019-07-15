package com.miracozkan.tvseries.datalayer.network.response

import com.miracozkan.tvseries.datalayer.model.Poster
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miracozkan.tvseries.datalayer.model.Backdrop


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 18:37            │
//└─────────────────────────────┘

data class GetImages(

    @SerializedName("backdrops")
    @Expose
    var backdrops: List<Backdrop>? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("posters")
    @Expose
    var posters: List<Poster>? = null

)