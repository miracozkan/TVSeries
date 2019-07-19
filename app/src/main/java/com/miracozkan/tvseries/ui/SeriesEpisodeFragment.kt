package com.miracozkan.tvseries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.datalayer.model.SeriesSeason
import kotlinx.android.synthetic.main.fragment_series_episode.*

private const val seriesID = "seriesID"

class SeriesEpisodeFragment : Fragment() {
    private var param1: ArrayList<SeriesSeason>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(seriesID)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_series_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recycSeasons) {
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<SeriesSeason>) =
                SeriesEpisodeFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(seriesID, param1)
                    }
                }
    }
}
