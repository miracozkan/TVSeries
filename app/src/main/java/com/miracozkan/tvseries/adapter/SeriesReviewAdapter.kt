package com.miracozkan.tvseries.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.datalayer.model.SeriesReviews


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 19/07/19 - 14:26            │
//└─────────────────────────────┘

class SeriesReviewAdapter(
        private var reviewsList: List<SeriesReviews>? = null
) : RecyclerView.Adapter<SeriesReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesReviewsViewHolder {
        return SeriesReviewsViewHolder(parent)
    }

    override fun getItemCount(): Int {
        reviewsList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: SeriesReviewsViewHolder, position: Int) {
        holder.bind(reviewsList!![position])
    }

    fun setNewItem(reviewsList: List<SeriesReviews>) {
        this.reviewsList = reviewsList
        notifyDataSetChanged()
    }

}