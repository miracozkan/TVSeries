package com.miracozkan.tvseries.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miracozkan.tvseries.di.ViewModelFactory
import com.miracozkan.tvseries.di.key.ViewModelKey
import com.miracozkan.tvseries.ui.series.search.SeriesSearchViewModel
import com.miracozkan.tvseries.ui.series.search.item.SeriesItemViewModel
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
}