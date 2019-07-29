package com.miracozkan.tvseries.datalayer.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 23/07/19 - 12:24            │
//└─────────────────────────────┘

@Entity(tableName = "series_next_episode_air")
@Parcelize
data class SeriesNextEpisodeToAir(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("air_date")
    @Expose
    var airDate: String? = null,
    @SerializedName("episode_number")
    @Expose
    var episodeNumber: Int? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,
    @SerializedName("production_code")
    @Expose
    var productionCode: String? = null,
    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int? = null,
    @SerializedName("show_id")
    @Expose
    var showId: Int? = null,
    @SerializedName("still_path")
    @Expose
    var stillPath: @RawValue Any? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Int? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

) : Parcelable