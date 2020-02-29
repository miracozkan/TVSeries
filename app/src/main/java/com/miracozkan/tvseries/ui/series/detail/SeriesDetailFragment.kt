package com.miracozkan.tvseries.ui.series.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.databinding.FragmentSeriesDetailBinding
import com.miracozkan.tvseries.ui.adapter.SeriesDetailPagerAdapter
import com.miracozkan.tvseries.utils.dissappearProgress
import com.miracozkan.tvseries.utils.extensions.injectViewModel

/**
 * A simple [Fragment] subclass.
 */
class SeriesDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentSeriesDetailBinding
    private lateinit var viewModel: SeriesDetailViewModel
    private val seriesId by lazy {
        SeriesDetailFragmentArgs.fromBundle(arguments!!).seriesId
    }

    private lateinit var webSite: String
    private lateinit var channelSite: String
    private lateinit var vpAdapter: SeriesDetailPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesDetailBinding.inflate(inflater, null, false)
        viewModel = injectViewModel(viewModelFactory)
        initUI()
        viewModel.setSeriesId(seriesId)
        return binding.root
    }

    private fun initUI() {
        binding.scrollView.dissappearProgress()
        binding.appBarLayout.dissappearProgress()
        binding.scalingLayout.dissappearProgress()
        vpAdapter = SeriesDetailPagerAdapter(this@SeriesDetailFragment, seriesId)
        binding.adapter = vpAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.seriesDetail.observe(viewLifecycleOwner, Observer {
            TODO()
        })

        viewModel.seriesReviews.observe(viewLifecycleOwner, Observer {
            TODO()
        })
    }
}

