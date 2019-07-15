package com.miracozkan.tvseries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
import com.miracozkan.tvseries.datalayer.repository.VideoRepository


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 10/05/19 - 19:21            │
//└─────────────────────────────┘


class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(PopularSeriesViewModel::class.java) -> {
                PopularSeriesViewModel(this.repository as PopularSeriesRepository) as T
            }
            modelClass.isAssignableFrom(VideoViewModel::class.java) -> {
                VideoViewModel(this.repository as VideoRepository) as T
            }
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}