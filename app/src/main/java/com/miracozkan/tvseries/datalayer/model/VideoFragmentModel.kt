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
//│ 15/07/19 - 00:29            │
//└─────────────────────────────┘

@Entity(tableName = "videp_fragment_model_table")
@Parcelize
data class VideoFragmentModel(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("original_name")
    @Expose
    var originalName: String? = null,
    @SerializedName("popularity")
    @Expose
    var popularity: Float? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    var posterList: List<Poster>? = null,

    @SerializedName("key")
    @Expose
    var key: String? = null
) : Parcelable