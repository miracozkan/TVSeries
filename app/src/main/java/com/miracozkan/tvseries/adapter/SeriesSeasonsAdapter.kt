package com.miracozkan.tvseries.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.datalayer.model.SeriesSeason


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 19/07/19 - 15:28            │
//└─────────────────────────────┘

class SeriesSeasonsAdapter(
    private var seasonsList: List<SeriesSeason>? = null
) : RecyclerView.Adapter<SeriesSeasonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesSeasonsViewHolder {
        return SeriesSeasonsViewHolder(parent)
    }

    override fun getItemCount(): Int {
        seasonsList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: SeriesSeasonsViewHolder, position: Int) {
        holder.bind(seasonsList!![position])
    }

    fun setNewItem(seasonsList: List<SeriesSeason>) {
        this.seasonsList = seasonsList
        notifyDataSetChanged()
    }
}