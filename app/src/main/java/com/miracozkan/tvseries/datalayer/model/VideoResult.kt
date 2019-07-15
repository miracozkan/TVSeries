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
//│ 13/07/19 - 18:19            │
//└─────────────────────────────┘

@Parcelize
data class VideoResult(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null,
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null,
    @SerializedName("key")
    @Expose
    var key: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("site")
    @Expose
    var site: String? = null,
    @SerializedName("size")
    @Expose
    var size: Int? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null
) : Parcelable