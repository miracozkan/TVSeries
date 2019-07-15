package com.miracozkan.tvseries.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.datalayer.model.Poster


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 18:46            │
//└─────────────────────────────┘

class SmallImageAdapter(
    private var imageList: List<Poster>? = null
) : RecyclerView.Adapter<SmallImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallImageViewHolder =
        SmallImageViewHolder(parent)

    override fun getItemCount(): Int {
        imageList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: SmallImageViewHolder, position: Int) {
        holder.bind(imageList!![position])
    }

    fun setNewItem(imageList: List<Poster>) {
        this.imageList = imageList
        notifyDataSetChanged()
    }
}