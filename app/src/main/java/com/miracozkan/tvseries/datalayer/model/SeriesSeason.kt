package com.miracozkan.tvseries.datalayer.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 10:21            │
//└─────────────────────────────┘

@Parcelize
data class SeriesSeason(

        @SerializedName("air_date")
        @Expose
        var airDate: String? = null,
        @SerializedName("episode_count")
        @Expose
        var episodeCount: Int? = null,
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("overview")
        @Expose
        var overview: String? = null,
        @SerializedName("poster_path")
        @Expose
        var posterPath: String? = null,
        @SerializedName("season_number")
        @Expose
        var seasonNumber: Int? = null
) : Parcelable