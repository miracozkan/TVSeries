package com.miracozkan.tvseries.ui.series.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.databinding.FragmentSeriesDetailBinding
import com.miracozkan.tvseries.ui.adapter.SeriesDetailPagerAdapter
import com.miracozkan.tvseries.utils.Status
import com.miracozkan.tvseries.utils.dissappearProgress
import com.miracozkan.tvseries.utils.extensions.injectViewModel
import com.miracozkan.tvseries.utils.hideProgress
import com.miracozkan.tvseries.utils.showProgress
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class SeriesDetailFragment : BaseFragment() {

    private val tabTitleList = listOf(
        "Detail | Reviews",
        "Episodes"
    )

    private lateinit var binding: FragmentSeriesDetailBinding
    private lateinit var viewModel: SeriesDetailsViewModel
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
        binding.vpSeriesDetail.adapter = vpAdapter
        TabLayoutMediator(
            binding.tblSeriesDetailTitle,
            binding.vpSeriesDetail,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = tabTitleList[position]
            }).attach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.seriesDetail.observe(viewLifecycleOwner, Observer { _seriesDetail ->
            when (_seriesDetail.status) {
                Status.ERROR -> {
                    binding.pb.hideProgress()
                    Toast.makeText(activity!!, _seriesDetail.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    binding.pb.hideProgress()
                    _seriesDetail.data?.run {
                        webSite = homepage ?: "Empty"
                        channelSite = networks?.let {
                            it.first().name ?: "Empty"
                        }.toString()
                        genres?.forEach { _it ->
                            binding.txtSeriesInfo.append(_it.name + " - ")
                        }
                        createdBy?.forEach { _it ->
                            binding.txtSeriesWritter.append(_it.name + " - ")
                        }
                        binding.txtSeriesName.text = name
                        binding.txtStoryLineDesc.text = overview
                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500$backdropPath")
                            .into(binding.imgSeriesVideo)
                    }
                    binding.appBarLayout.showProgress()
                    binding.scalingLayout.showProgress()
                    binding.scrollView.showProgress()

                }
                Status.LOADING -> {
                    binding.pb.showProgress()
                }
            }
            // TODO()
        })

        viewModel.seriesReviews.observe(viewLifecycleOwner, Observer {
            // TODO()
        })
    }
}

