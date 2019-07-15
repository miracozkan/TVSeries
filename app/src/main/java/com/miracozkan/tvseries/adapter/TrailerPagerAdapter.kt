package com.miracozkan.tvseries.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.model.Poster
import com.miracozkan.tvseries.datalayer.model.VideoFragmentModel
import com.miracozkan.tvseries.datalayer.model.VideoResult
import com.miracozkan.tvseries.ui.VideoFragment


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 13/07/19 - 16:53            │
//└─────────────────────────────┘

class TrailerPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var seriesData: List<PopularSeriesResult> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return VideoFragment.newInstance(seriesData[position])
    }

    override fun getCount(): Int {
        return seriesData.size
    }

    fun setVideoList(seriesData: List<PopularSeriesResult>) {
        this.seriesData = seriesData
        notifyDataSetChanged()
    }

}