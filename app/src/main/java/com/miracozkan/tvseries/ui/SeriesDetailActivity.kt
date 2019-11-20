package com.miracozkan.tvseries.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.SeriesDetailViewPagerAdapter
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.reciever.InternetConnectionReciever
import com.miracozkan.tvseries.utils.*
import com.miracozkan.tvseries.viewmodel.SeriesDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_series_detail.*


class SeriesDetailActivity : AppCompatActivity(), View.OnClickListener {

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
    private lateinit var webSite: String
    private lateinit var channelSite: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_detail)

        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        scrollView.dissappearProgress()
        appBarLayout.dissappearProgress()
        scalingLayout.dissappearProgress()

        with(vpSeriesDetail) {
            adapter = SeriesDetailViewPagerAdapter(supportFragmentManager, seriesID)
            tblSeriesDetailTitle.setupWithViewPager(this)
        }
        lytOpenWeb.setOnClickListener(this)
        lytOpenChannel.setOnClickListener(this)
        seriesDetailViewModel.seriesDetail.observe(this, Observer { _seriesDetail ->
            when (_seriesDetail) {
                is Resource.Failure -> {
                    pb.hideProgress()
                    Toast.makeText(this, _seriesDetail.cause, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    pb.hideProgress()
                    _seriesDetail.run {
                        webSite = data?.homepage!!
                        channelSite = data.networks?.first()?.name!!
                        data.genres?.forEach { _it ->
                            txtSeriesInfo.append(_it.name + " - ")
                        }
                        data.createdBy?.forEach { _it ->
                            txtSeriesWritter.append(_it.name + " - ")
                        }
                        txtSeriesName.text = data.name
                        txtStoryLineDesc.text = data.overview
                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500" + data.backdropPath)
                            .into(imgSeriesVideo)
                    }
                    appBarLayout.showProgress()
                    scalingLayout.showProgress()
                    scrollView.showProgress()

                }
                is Resource.Loading -> {
                    pb.showProgress()
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.lytOpenWeb -> {
                Toast.makeText(this, webSite, Toast.LENGTH_SHORT).show()
            }
            R.id.lytOpenChannel -> {
                Toast.makeText(this, channelSite, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        registerReceiver(
            internetConnectionReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(internetConnectionReceiver)
    }
}
