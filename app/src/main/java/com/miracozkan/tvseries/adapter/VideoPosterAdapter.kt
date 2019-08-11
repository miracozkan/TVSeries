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

class VideoPosterAdapter(
        private var imageList: List<Poster>? = null,
        private val onItemClickListener: (Poster) -> Unit
) : RecyclerView.Adapter<VideoPosterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPosterViewHolder =
            VideoPosterViewHolder(parent)

    override fun getItemCount(): Int {
        imageList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: VideoPosterViewHolder, position: Int) {
        holder.bind(imageList!![position], onItemClickListener)
    }

    fun setNewItem(imageList: List<Poster>) {
        this.imageList = imageList
        notifyDataSetChanged()
    }
}