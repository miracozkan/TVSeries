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
//│ 13/07/19 - 16:37            │
//└─────────────────────────────┘

@Entity(tableName = "popular_series_result_table")
@Parcelize
data class PopularSeriesResult(
    @PrimaryKey(autoGenerate = true)
    val sid: Int,
    @SerializedName("original_name")
    @Expose
    val originalName: String,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("popularity")
    @Expose
    val popularity: Float? = null,
    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String>? = null,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null,
    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String? = null,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float? = null,
    @SerializedName("overview")
    @Expose
    val overview: String? = null,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null
) : Parcelable