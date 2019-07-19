package com.miracozkan.tvseries.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.miracozkan.tvseries.datalayer.model.SeriesSeason
import com.miracozkan.tvseries.ui.SeriesDetailsFragment
import com.miracozkan.tvseries.ui.SeriesEpisodeFragment


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 17/07/19 - 11:40            │
//└─────────────────────────────┘

class SeriesDetailViewPagerAdapter(
        fm: FragmentManager,
        private val seriesID: Int,
        private val seriesSeason: List<SeriesSeason>
) : FragmentPagerAdapter(fm) {

    private val seriesDetailFragment by lazy {
        SeriesDetailsFragment.newInstance(seriesID)
    }
    private val seriesEpisodeFragnent by lazy {
        SeriesEpisodeFragment.newInstance(seriesSeason as ArrayList<SeriesSeason>)
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

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

}