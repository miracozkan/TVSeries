package com.miracozkan.tvseries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miracozkan.tvseries.adapter.SeriesDetailViewPagerAdapter
import com.miracozkan.tvseries.datalayer.model.SeriesSeason
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.utils.ViewModelFactory
import com.miracozkan.tvseries.viewmodel.SeriesDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_series_detail.*


class SeriesDetailActivity : AppCompatActivity() {

    private val seriesID = intent.extras!!.getInt("seriesData")

    private val seriesDetailRepository by lazy {
        DependencyUtil.getSeriesDetailRepository(RetrofitClient.getClient(), seriesID = seriesID)
    }
    private val seriesDetailViewModel by lazy {
        ViewModelProviders.of(
                this,
                ViewModelFactory(seriesDetailRepository)
        ).get(SeriesDetailViewModel::class.java)
    }

    private var seriesGenres: String? = null
    private var seriesWritters: String? = null
    private val seriesSeasons: ArrayList<SeriesSeason> = ArrayList<SeriesSeason>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.miracozkan.tvseries.R.layout.activity_series_detail)

        seriesDetailViewModel.seriesDetail.observe(this, Observer { _seriesDetail ->
            _seriesDetail.genres?.forEach { _it ->
                seriesGenres += _it.name + " | "
            }
            _seriesDetail.createdBy?.forEach { _it ->
                seriesWritters += _it.name + " | "
            }
            txtSeriesInfo.text = seriesGenres
            txtSeriesName.text = _seriesDetail.name
            txtSeriesWritter.text = seriesWritters
            txtStoryLineDesc.text = _seriesDetail.overview
            Picasso
                    .get()
                    .load("https://image.tmdb.org/t/p/w300" + _seriesDetail.backdropPath)
                    .centerCrop()
                    .into(imgSeriesVideo)
            Picasso
                    .get()
                    .load("https://image.tmdb.org/t/p/w300" + _seriesDetail.posterPath)
                    .centerCrop()
                    .into(imgSeriesPoster)
            seriesSeasons.addAll(_seriesDetail.seasons!!)
        })

        with(vpSeriesDetail) {
            adapter = SeriesDetailViewPagerAdapter(supportFragmentManager, seriesID, seriesSeasons)
            tblSeriesDetailTitle.setupWithViewPager(this)
            currentItem = 1
        }
    }
}
