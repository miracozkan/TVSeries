package com.miracozkan.tvseries.di.module

import com.miracozkan.tvseries.ui.series.search.SeriesSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 12:00          │
//└─────────────────────────────┘

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSeriesSearchFragment(): SeriesSearchFragment

}