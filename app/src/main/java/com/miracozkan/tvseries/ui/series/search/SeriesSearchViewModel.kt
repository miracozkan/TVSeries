package com.miracozkan.tvseries.ui.series.search

import androidx.lifecycle.ViewModel
import com.miracozkan.tvseries.repository.SeriesRepository
import javax.inject.Inject


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 11:59          │
//└─────────────────────────────┘

class SeriesSearchViewModel @Inject constructor(seriesRepository: SeriesRepository) : ViewModel() {

    private val _seriesList = seriesRepository.getSeriesList()
    val seriesList get() = _seriesList

}