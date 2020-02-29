package com.miracozkan.tvseries.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miracozkan.tvseries.ui.fragment.SeriesDetailsFragment
import com.miracozkan.tvseries.ui.fragment.SeriesEpisodeFragment


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
    private val fragmentTitleList: ArrayList<String>

    init {
        fragmentList = ArrayList<Fragment>().apply {
            add(seriesDetailFragment)
            add(seriesEpisodeFragnent)
        }
        fragmentTitleList = ArrayList<String>().apply {
            add("Detail | Reviews")
            add("Episodes")
        }
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}