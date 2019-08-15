package com.miracozkan.tvseries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.datalayer.model.SeriesReviews


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 19/07/19 - 14:27            │
//└─────────────────────────────┘

class SeriesReviewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_user_reviews, parent, false)
) {
    private val txtReview = itemView.findViewById<TextView>(R.id.txtReview)
    private val txtReviewTitle = itemView.findViewById<TextView>(R.id.txtReviewTitle)
    private val txtReviewDetail = itemView.findViewById<TextView>(R.id.txtReviewDetail)


    @SuppressLint("SetTextI18n")
    fun bind(model: SeriesReviews) {
        txtReview.text = model.content
        txtReviewDetail.text = "Written by " + model.author
    }

}