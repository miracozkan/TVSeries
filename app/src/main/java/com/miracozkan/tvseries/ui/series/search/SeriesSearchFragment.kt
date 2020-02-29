package com.miracozkan.tvseries.ui.series.search


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.databinding.FragmentSeriesSearchBinding
import com.miracozkan.tvseries.ui.adapter.SeriesListPagerAdapter
import com.miracozkan.tvseries.utils.Status.*
import com.miracozkan.tvseries.utils.extensions.injectViewModel

/**
 * A simple [Fragment] subclass.
 */
class SeriesSearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSeriesSearchBinding

    private lateinit var viewModel: SeriesSearchViewModel
    private lateinit var adapter: SeriesListPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesSearchBinding.inflate(inflater, null, false)
        adapter = SeriesListPagerAdapter(this)
        viewModel = injectViewModel(viewModelFactory)
        binding.adapter = adapter
        initUI()
        return binding.root
    }

    private fun initUI() {
        activity!!.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.seriesList.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                SUCCESS -> {
                    adapter.setNewItemList(result.data ?: emptyList())
                }
                ERROR -> {
                    Snackbar.make(view, result.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
                LOADING -> {
                    Log.e("Loading : ", "Loading")
                }
            }
        })
    }
}
