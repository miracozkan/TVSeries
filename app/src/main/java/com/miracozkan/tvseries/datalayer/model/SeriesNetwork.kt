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
//│ 18/07/19 - 10:17            │
//└─────────────────────────────┘

@Entity(tableName = "series_network_table")
@Parcelize
data class SeriesNetwork(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

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

) : Parcelable