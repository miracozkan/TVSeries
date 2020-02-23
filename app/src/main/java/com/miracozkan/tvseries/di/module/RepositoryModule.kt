package com.miracozkan.tvseries.di.module

import com.miracozkan.tvseries.repository.SeriesRepository
import com.miracozkan.tvseries.repository.SeriesRepositoryImpl
import dagger.Binds


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 12:42          │
//└─────────────────────────────┘

abstract class RepositoryModule {
    @Binds
    abstract fun provideSeriesRepository(seriesRepositoryImpl: SeriesRepositoryImpl): SeriesRepository
}