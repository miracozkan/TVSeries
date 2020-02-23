package com.miracozkan.tvseries.ui.series.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miracozkan.tvseries.databinding.FragmentSeriesDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class SeriesDetailFragment : Fragment() {

    private lateinit var binding: FragmentSeriesDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesDetailBinding.inflate(inflater, null, false)
        return binding.root
    }
}
