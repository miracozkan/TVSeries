package com.miracozkan.tvseries.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miracozkan.tvseries.ui.detail.SeriesDetailsFragment
import com.miracozkan.tvseries.ui.detail.SeriesEpisodeFragment


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 24.02.2020 - 20:18          │
//└─────────────────────────────┘

class SeriesDetailPagerAdapter(fa: Fragment, seriesID: Int) : FragmentStateAdapter(fa) {

    private val seriesDetailFragment by lazy {
        SeriesDetailsFragment.newInstance(seriesID)
    }
    private val seriesEpisodeFragnent by lazy {
        SeriesEpisodeFragment.newInstance(seriesID)
    }

    private val fragmentList: ArrayList<Fragment>

    init {
        fragmentList = ArrayList<Fragment>().apply {
            add(seriesDetailFragment)
            add(seriesEpisodeFragnent)
        }
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}