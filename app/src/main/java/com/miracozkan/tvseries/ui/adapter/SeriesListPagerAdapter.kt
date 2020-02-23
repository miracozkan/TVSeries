package com.miracozkan.tvseries.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.ui.series.search.item.SeriesItemFragment


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 23.02.2020 - 12:20          │
//└─────────────────────────────┘

class SeriesListPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    private var seriesList = listOf<PopularSeriesResult>()

    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle().apply {
            putParcelable("seriesData", seriesList[position])
        }
        return SeriesItemFragment().apply {
            arguments = bundle
        }
    }

    fun setNewItemList(seriesList: List<PopularSeriesResult>) {
        this.seriesList = seriesList
        notifyDataSetChanged()
    }
}