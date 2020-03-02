package com.miracozkan.tvseries.di.module

import com.miracozkan.tvseries.ui.detail.SeriesDetailsFragment
import com.miracozkan.tvseries.ui.detail.SeriesEpisodeFragment
import com.miracozkan.tvseries.ui.series.detail.SeriesDetailFragment
import com.miracozkan.tvseries.ui.series.search.SeriesSearchFragment
import com.miracozkan.tvseries.ui.series.search.item.SeriesItemFragment
import com.miracozkan.tvseries.ui.video.VideoFragment
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

    @ContributesAndroidInjector
    abstract fun contributeSeriesItemFragment(): SeriesItemFragment

    @ContributesAndroidInjector
    abstract fun contributeSeriesDetailFragment(): SeriesDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeSeriesDetailsFragment(): SeriesDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeSeriesEpisodeFragment(): SeriesEpisodeFragment

    @ContributesAndroidInjector
    abstract fun construbuteVideoFragment(): VideoFragment


}