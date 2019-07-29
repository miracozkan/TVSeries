package com.miracozkan.tvseries.datalayer.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.miracozkan.tvseries.datalayer.model.VideoResult


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 18:19            │
//└─────────────────────────────┘

@Entity(tableName = "get_video_table")
data class GetVideo(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("results")
    @Expose
    var results: List<VideoResult>? = null

)