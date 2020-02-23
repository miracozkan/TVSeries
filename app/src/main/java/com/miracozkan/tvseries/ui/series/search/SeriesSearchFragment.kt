package com.miracozkan.tvseries.ui.series.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miracozkan.tvseries.databinding.FragmentSeriesSearchBinding

/**
 * A simple [Fragment] subclass.
 */
class SeriesSearchFragment : Fragment() {

    private lateinit var binding: FragmentSeriesSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesSearchBinding.inflate(inflater, null, false)
        return binding.root
    }
}
