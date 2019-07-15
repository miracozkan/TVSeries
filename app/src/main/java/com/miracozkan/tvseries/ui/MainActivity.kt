package com.miracozkan.tvseries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.TrailerPagerAdapter
import com.miracozkan.tvseries.datalayer.model.VideoFragmentModel
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.viewmodel.PopularSeriesViewModel
import com.miracozkan.tvseries.viewmodel.VideoViewModel
import com.miracozkan.tvseries.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var param1: Int? = null

    private val seriesList by lazy { ArrayList<VideoFragmentModel>() }

    private val popularSeriesRepository by lazy {
        DependencyUtil.getPopularSeriesRepository(RetrofitClient.getClient())
    }
    private val popularSeriesViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelFactory(popularSeriesRepository)
        ).get(PopularSeriesViewModel::class.java)
    }

//    private val videoRepository by lazy {
//        DependencyUtil.getVideoRepository(RetrofitClient.getClient(), param1.toString())
//    }
//    private val videoViewModel by lazy {
//        ViewModelProviders.of(
//            this,
//            ViewModelFactory(videoRepository)
//        ).get(VideoViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seriesData: VideoFragmentModel by lazy { VideoFragmentModel() }

        popularSeriesViewModel.popularSeriesList.observe(this, Observer { _seriesList ->
            (vpTrailers.adapter as TrailerPagerAdapter).setVideoList(_seriesList!!)
        })

        vpTrailers.apply {
            adapter = TrailerPagerAdapter(supportFragmentManager)
            currentItem = 5
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        popularSeriesViewModel.cancelRequests()
    }
}
