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
//│ 18/07/19 - 10:15            │
//└─────────────────────────────┘

@Entity(tableName = "series_created_by_table")
@Parcelize
data class SeriesCreatedBy(

    @PrimaryKey(autoGenerate = true)
    var sid: Int? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("credit_id")
    @Expose
    var creditId: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("gender")
    @Expose
    var gender: Int? = null,
    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null

) : Parcelable