package com.miracozkan.tvseries.ui.series.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.databinding.FragmentSeriesDetailBinding
import com.miracozkan.tvseries.ui.adapter.SeriesDetailPagerAdapter
import com.miracozkan.tvseries.utils.Status
import com.miracozkan.tvseries.utils.dissappearProgress
import com.miracozkan.tvseries.utils.extensions.injectViewModel
import com.miracozkan.tvseries.utils.hideProgress
import com.miracozkan.tvseries.utils.showProgress
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_series_detail.*

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

        viewModel.seriesDetail.observe(viewLifecycleOwner, Observer { _seriesDetail ->
            when (_seriesDetail.status) {
                Status.ERROR -> {
                    pb.hideProgress()
                    Toast.makeText(activity!!, _seriesDetail.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    pb.hideProgress()
                    _seriesDetail.run {
                        webSite = data?.homepage!!
                        channelSite = data.networks?.first()?.name!!
                        data.genres?.forEach { _it ->
                            binding.txtSeriesInfo.append(_it.name + " - ")
                        }
                        data.createdBy?.forEach { _it ->
                            binding.txtSeriesWritter.append(_it.name + " - ")
                        }
                        binding.txtSeriesName.text = data.name
                        binding.txtStoryLineDesc.text = data.overview
                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500" + data.backdropPath)
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

