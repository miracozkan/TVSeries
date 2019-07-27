package com.miracozkan.tvseries.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miracozkan.tvseries.datalayer.repository.PopularSeriesRepository
import com.miracozkan.tvseries.datalayer.repository.SeriesDetailRepository
import com.miracozkan.tvseries.datalayer.repository.VideoRepository
import com.miracozkan.tvseries.viewmodel.PopularSeriesViewModel
import com.miracozkan.tvseries.viewmodel.SeriesDetailViewModel
import com.miracozkan.tvseries.viewmodel.VideoViewModel


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
            modelClass.isAssignableFrom(SeriesDetailViewModel::class.java) -> {
                SeriesDetailViewModel(this.repository as SeriesDetailRepository) as T
            }
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}