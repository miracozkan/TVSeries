package com.miracozkan.tvseries.ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.TrailerPagerAdapter
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.*
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

        /**
         * For Fullscreen Videos
         */

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        popularSeriesViewModel.popularSeriesList.observe(this, Observer { _resource ->
            when (_resource) {
                is Resource.Loading -> {
                    showProgress()
                }
                is Resource.Failure -> {
                    hideProgress()
                    Toast.makeText(this, _resource.cause, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    hideProgress()
                    (vpTrailers.adapter as TrailerPagerAdapter).setVideoList(_resource.data?.getContentIfHandled()!!)
                }
            }
        })

        vpTrailers.apply {
            adapter = TrailerPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
        }
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