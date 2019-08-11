package com.miracozkan.tvseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.datalayer.model.Poster
import com.squareup.picasso.Picasso


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 18:47            │
//└─────────────────────────────┘

class VideoPosterViewHolder(private val parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_small_trailer, parent, false)
) {

    fun bind(model: Poster, onItemClickListener: (Poster) -> Unit) {
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w200" + model.filePath)
                .resize(108, 192)
                .centerCrop()
                .into(itemView.findViewById<AppCompatImageView>(R.id.imgThumbnail))

        itemView.setOnClickListener {
            onItemClickListener(model)
        }
    }
}
