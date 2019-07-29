package com.miracozkan.tvseries.datalayer.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 18:37            │
//└─────────────────────────────┘

@Entity(tableName = "poster_table")
@Parcelize
data class Poster(
    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Float? = null,
    @SerializedName("file_path")
    @Expose
    var filePath: String? = null,
    @SerializedName("height")
    @Expose
    var height: Int? = null,
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,
    @SerializedName("width")
    @Expose
    var width: Int? = null

) : Parcelable