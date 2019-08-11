package com.miracozkan.tvseries.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.TrailerPagerAdapter
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.utils.ViewModelFactory
import com.miracozkan.tvseries.viewmodel.PopularSeriesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val popularSeriesRepository by lazy {
        DependencyUtil.getPopularSeriesRepository(
                RetrofitClient.getClient()
        )
    }

    private val popularSeriesViewModel by lazy {
        ViewModelProviders.of(
                this,
                ViewModelFactory(popularSeriesRepository)
        ).get(PopularSeriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onStart() {
        super.onStart()

        popularSeriesViewModel.popularSeriesList.observe(this, Observer { _seriesList ->
            (vpTrailers.adapter as TrailerPagerAdapter).setVideoList(_seriesList!!)
        })

        vpTrailers.apply {
            adapter = TrailerPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
        }
    }
}