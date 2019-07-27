package com.miracozkan.tvseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.datalayer.model.SeriesCreatedBy
import com.squareup.picasso.Picasso


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 20/07/19 - 14:28            │
//└─────────────────────────────┘

class SeriesCastViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_cast, parent, false)
) {

    private val txtCastName = itemView.findViewById<TextView>(R.id.txtPlayerNameSurname)
    private val imgCastPhoto = itemView.findViewById<ImageView>(R.id.imgPlayerPhoto)

    fun bind(model: SeriesCreatedBy) {
        txtCastName.text = model.name
        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/w200" + model.profilePath)
            .into(imgCastPhoto)
    }
}