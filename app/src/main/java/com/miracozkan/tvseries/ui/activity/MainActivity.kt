package com.miracozkan.tvseries.ui.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.TrailerPagerAdapter
import com.miracozkan.tvseries.base.BaseActivity
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.*
import com.miracozkan.tvseries.viewmodel.PopularSeriesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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

        initUi()
        initObserver()
    }

    private fun initUi() {

        /**
         * For Fullscreen Videos
         */
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        vpTrailers.apply {
            adapter = TrailerPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
        }
    }

    private fun initObserver() {
        popularSeriesViewModel.popularSeriesList.observe(this, Observer { _resource ->
            when (_resource.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    hideProgress()
                    Toast.makeText(this, _resource.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    hideProgress()
                    (vpTrailers.adapter as TrailerPagerAdapter).setVideoList(_resource.data.orEmpty())
                }
            }
        })

    }

    /**
     * This interface can be unnecessary
     */
    private fun hideProgress() {
        prg.hideProgress()
    }

    private fun showProgress() {
        prg.showProgress()
    }
}