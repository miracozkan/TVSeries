package com.miracozkan.tvseries.ui.series.search.item


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miracozkan.tvseries.databinding.FragmentSeriesItemBinding

/**
 * A simple [Fragment] subclass.
 */
class SeriesItemFragment : Fragment() {

    private lateinit var binding: FragmentSeriesItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesItemBinding.inflate(inflater, null, false)
        return binding.root
    }
}
