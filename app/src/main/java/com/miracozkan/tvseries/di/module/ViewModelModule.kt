package com.miracozkan.tvseries.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miracozkan.tvseries.di.ViewModelFactory
import com.miracozkan.tvseries.di.key.ViewModelKey
import com.miracozkan.tvseries.ui.detail.SeriesDetailViewModel
import com.miracozkan.tvseries.ui.series.detail.SeriesDetailsViewModel
import com.miracozkan.tvseries.ui.series.search.SeriesSearchViewModel
import com.miracozkan.tvseries.ui.series.search.item.SeriesItemViewModel
import com.miracozkan.tvseries.ui.video.VideoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 11:59          │
//└─────────────────────────────┘

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SeriesSearchViewModel::class)
    abstract fun bindSeriesSearchViewModel(seriesSearchViewModel: SeriesSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeriesItemViewModel::class)
    abstract fun bindSeriesItemViewModel(seriesItemViewModel: SeriesItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeriesDetailViewModel::class)
    abstract fun bindSeriesDetailViewModel(seriesDetailViewModel: SeriesDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VideoViewModel::class)
    abstract fun bindVideoViewModel(videoViewModel: VideoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeriesDetailsViewModel::class)
    abstract fun bindSeriesDetailsViewModel(seriesDetailsViewModel: SeriesDetailsViewModel): ViewModel

}