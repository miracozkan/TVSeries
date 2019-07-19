package com.miracozkan.tvseries.datalayer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 18/07/19 - 10:16            │
//└─────────────────────────────┘

data class SeriesLastEpisode(

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
        var stillPath: String? = null,
        @SerializedName("vote_average")
        @Expose
        var voteAverage: Int? = null,
        @SerializedName("vote_count")
        @Expose
        var voteCount: Int? = null

)