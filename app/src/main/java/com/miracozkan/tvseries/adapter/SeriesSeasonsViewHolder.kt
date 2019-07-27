package com.miracozkan.tvseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.datalayer.model.SeriesSeason
import com.squareup.picasso.Picasso


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 19/07/19 - 15:28            │
//└─────────────────────────────┘

class SeriesSeasonsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_series_seasons, parent, false)
) {

    private val txtSeriesSeaonsNumber = itemView.findViewById<TextView>(R.id.txtSeriesSeaonsNumber)
    private val txtSeriesSeaonsOverview = itemView.findViewById<TextView>(R.id.txtSeriesSeaonsOverview)
    private val imgSeriesSeaonsPoster = itemView.findViewById<ImageView>(R.id.imgSeriesSeaonsPoster)
    private val txtSeriesSeasonEpCount = itemView.findViewById<TextView>(R.id.txtSeriesSeasonEpCount)

    fun bind(model: SeriesSeason) {
        txtSeriesSeaonsNumber.text = model.name
        txtSeriesSeaonsOverview.text = model.overview
        txtSeriesSeasonEpCount.text = "Episode Count : ${model.episodeCount}"
        Picasso.get().load("https://image.tmdb.org/t/p/w200" + model.posterPath).into(imgSeriesSeaonsPoster)
    }
}