package com.miracozkan.tvseries.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.datalayer.model.SeriesCreatedBy


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 20/07/19 - 14:28            │
//└─────────────────────────────┘

class SeriesCastAdapter(
    private var castList: List<SeriesCreatedBy>? = null
) : RecyclerView.Adapter<SeriesCastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesCastViewHolder {
        return SeriesCastViewHolder(parent)
    }

    override fun getItemCount(): Int {
        castList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: SeriesCastViewHolder, position: Int) {
        holder.bind(castList!![position])
    }

    fun setNewItem(castList: List<SeriesCreatedBy>) {
        this.castList = castList
        notifyDataSetChanged()
    }

}