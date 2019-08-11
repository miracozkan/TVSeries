package com.miracozkan.tvseries.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miracozkan.tvseries.adapter.SeriesDetailViewPagerAdapter
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.reciever.InternetConnectionReciever
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.utils.ViewModelFactory
import com.miracozkan.tvseries.viewmodel.SeriesDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_series_detail.*


class SeriesDetailActivity : AppCompatActivity() {

    private val internetConnectionReceiver by lazy { InternetConnectionReciever() }

    private val seriesID by lazy { intent.extras!!.getInt("seriesID") }

    private val seriesDetailRepository by lazy {
        DependencyUtil.getSeriesDetailRepository(
                RetrofitClient.getClient(),
                seriesID = seriesID
        )
    }
    private val seriesDetailViewModel by lazy {
        ViewModelProviders.of(
                this,
                ViewModelFactory(seriesDetailRepository)
        ).get(SeriesDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.miracozkan.tvseries.R.layout.activity_series_detail)

        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        scrollView.visibility = View.INVISIBLE
        appBarLayout.visibility = View.INVISIBLE
        scalingLayout.visibility = View.INVISIBLE

        with(vpSeriesDetail) {
            adapter = SeriesDetailViewPagerAdapter(supportFragmentManager, seriesID)
            tblSeriesDetailTitle.setupWithViewPager(this)
        }

        seriesDetailViewModel.seriesDetail.observe(this, Observer { _seriesDetail ->
            _seriesDetail.genres?.forEach { _it ->
                txtSeriesInfo.append(_it.name + " - ")
            }
            _seriesDetail.createdBy?.forEach { _it ->
                txtSeriesWritter.append(_it.name + " - ")
            }
            txtSeriesName.text = _seriesDetail.name
            txtStoryLineDesc.text = _seriesDetail.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + _seriesDetail.backdropPath).into(imgSeriesVideo)
            appBarLayout.visibility = View.VISIBLE
            scalingLayout.visibility = View.VISIBLE
            pb.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        })
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(internetConnectionReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(internetConnectionReceiver)
    }
}
