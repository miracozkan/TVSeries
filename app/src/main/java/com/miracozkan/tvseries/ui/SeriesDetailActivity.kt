package com.miracozkan.tvseries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SeriesDetailActivity : AppCompatActivity() {

//    private val internetConnectionReceiver by lazy { InternetConnectionReciever() }
//
//    private val seriesID by lazy { intent.extras!!.getInt("seriesID") }
//
//    private val seriesDetailRepository by lazy {
//        DependencyUtil.getSeriesDetailRepository(
//            RetrofitClient.getClient(),
//            seriesID = seriesID,
//            projectDao = ProjectDatabase.getInstance(this).projectDao()
//        )
//    }
//    private val seriesDetailViewModel by lazy {
//        ViewModelProviders.of(
//            this,
//            ViewModelFactory(seriesDetailRepository)
//        ).get(SeriesDetailViewModel::class.java)
//    }
//
//    private var seriesGenres: String? = null
//    private var seriesWritters: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.miracozkan.tvseries.R.layout.activity_series_detail)


//        with(vpSeriesDetail) {
//            adapter = SeriesDetailViewPagerAdapter(supportFragmentManager, seriesID)
//            tblSeriesDetailTitle.setupWithViewPager(this)
//        }
//
//        seriesDetailViewModel.seriesDetail.observe(this, Observer { _seriesDetail ->
//            _seriesDetail.genres?.forEach { _it ->
//                seriesGenres += _it.name + " | "
//            }
//            _seriesDetail.createdBy?.forEach { _it ->
//                seriesWritters += _it.name + " | "
//            }
//            txtSeriesInfo.text = seriesGenres
//            txtSeriesName.text = _seriesDetail.name
//            txtSeriesWritter.text = seriesWritters
//            txtStoryLineDesc.text = _seriesDetail.overview
//            Picasso.get().load("https://image.tmdb.org/t/p/w500" + _seriesDetail.backdropPath).into(imgSeriesVideo)
//            Picasso.get().load("https://image.tmdb.org/t/p/w300" + _seriesDetail.posterPath).into(imgSeriesPoster)
//        })
    }

//    override fun onResume() {
//        super.onResume()
//        registerReceiver(internetConnectionReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
//    }
//
//    override fun onPause() {
//        super.onPause()
//        unregisterReceiver(internetConnectionReceiver)
//    }
}
