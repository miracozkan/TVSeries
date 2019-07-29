package com.miracozkan.tvseries.datalayer.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miracozkan.tvseries.datalayer.model.Backdrop
import com.miracozkan.tvseries.datalayer.model.Poster


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 18:37            │
//└─────────────────────────────┘

@Entity(tableName = "get_images_table")
data class GetImages(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

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