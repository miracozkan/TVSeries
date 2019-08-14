package com.miracozkan.tvseries.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.util.Util
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.VideoPosterAdapter
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.utils.ViewModelFactory
import com.miracozkan.tvseries.viewmodel.VideoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottom_sheet_video.*
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.layout_video.*


class VideoFragment : Fragment(), View.OnClickListener {

    private val detailActivityIntent by lazy { Intent(activity, SeriesDetailActivity::class.java) }
    private lateinit var param1: PopularSeriesResult
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    private lateinit var bandwidthMeter: DefaultBandwidthMeter

    private val videoRepository by lazy {
        DependencyUtil.getVideoRepository(RetrofitClient.getClient(), param1.id.toString())
    }
    private val videoViewModel by lazy {
        ViewModelProviders.of(
                this,
                ViewModelFactory(videoRepository)
        ).get(VideoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable("seriesData")!!
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)

    }

    @SuppressLint("SwitchIntDef")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBottomDetail.setOnClickListener(this)
        val bottomSheet = BottomSheetBehavior.from(lytBottomSheet)

        bottomSheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {//Acıldı
                        recycImages.visibility = View.INVISIBLE
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {//Kapandı
                        recycImages.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                Log.e("state", "onSlide")
            }
        })


        /**
         * Set UI component text
         */

        txtStoryLineDescBottom.text = param1.overview
        txtBottomCountry.text = param1.originCountry?.first()
        txtBottomDate.text = param1.firstAirDate
        txtBottomLang.text = param1.originalLanguage
        txtBottomName.text = param1.originalName

        txtTitle.text = param1.originalName
        txtLike.text = param1.voteCount.toString()
        txtDislike.text = param1.voteAverage.toString()

        txtDislike.setOnClickListener(this)
        txtLike.setOnClickListener(this)

        bandwidthMeter = DefaultBandwidthMeter()
        mediaDataSourceFactory = buildDataSourceFactory(true)

        if (exoPlayer == null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(
                    activity,
                    DefaultTrackSelector(), DefaultLoadControl()
            )
        }

        pvExoPlayer.apply {
            player = exoPlayer
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        }

        with(recycImages) {

            adapter = VideoPosterAdapter { _poster ->
                val myDialog = Dialog(activity!!).apply {
                    setContentView(R.layout.dialog_poster_detail)
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
                val poster = myDialog.findViewById<ImageView>(R.id.imgPosterDetail)
                Picasso
                        .get()
                        .load("https://image.tmdb.org/t/p/w500" + _poster.filePath)
                        .resize(216, 384)
                        .into(poster)
                poster.setOnClickListener {
                    Toast.makeText(context, _poster.filePath, Toast.LENGTH_SHORT).show()
                }
                myDialog.show()
            }

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        /**
         * Network Req
         */

        videoViewModel.seriesVideo.observe(this, Observer { _videoList ->
            if (!_videoList.isNullOrEmpty()) {
                releaseExo(_videoList.last().key!!)
            } else {
                releaseExo("iwNp2E1aV3Q")
                Toast.makeText(
                        activity,
                        "This TV Series Has Not Trailer",
                        Toast.LENGTH_LONG
                ).show()
            }
        })

        videoViewModel.seriesImage.observe(this, Observer { _posterList ->
            if (!_posterList.isNullOrEmpty()) {
                (recycImages.adapter as VideoPosterAdapter).setNewItem(_posterList)
                shimmer_view_container.stopShimmerAnimation()
                shimmer_view_container.visibility = View.GONE
            }
        })
    }

    @SuppressLint("StaticFieldLeak")
    private fun releaseExo(url: String) {
        exoPlayer?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        object : YouTubeExtractor(context!!) {
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta) {
                val iTag = 18 // 135 -> 480p, 18 -> 360p, 17 -> 144p
                if (ytFiles?.get(iTag) != null && ytFiles.get(iTag).url != null) {
                    val downloadUrl = ytFiles.get(iTag).url
                    val extractorsFactory = DefaultExtractorsFactory()
                    val mediaSource = ExtractorMediaSource.Factory(mediaDataSourceFactory)
                            .setExtractorsFactory(extractorsFactory)
                            .createMediaSource(Uri.parse(downloadUrl))
                    exoPlayer?.prepare(mediaSource, true, false)
                    exoPlayer?.seekTo(5000)
                }
                barProg.visibility = View.GONE

            }
        }.extract("http://youtube.com/watch?v=$url", true, true)
    }

    /**
     * ExoPlayer
     */

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            startPlayer()
        } else {
            pausePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmerAnimation()
        if (userVisibleHint)
            startPlayer()
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        shimmer_view_container.stopShimmerAnimation()
        pausePlayer()
    }

    /**
     *
     */

    private fun releasePlayer() {
        exoPlayer?.release()
    }


    private fun pausePlayer() {
        exoPlayer?.playWhenReady = false
        exoPlayer?.playbackState
    }

    private fun startPlayer() {
        exoPlayer?.playWhenReady = true
        exoPlayer?.playbackState
    }

    /**
     *
     */

    private fun buildDataSourceFactory(useBandwidthMeter: Boolean): DataSource.Factory {
        return buildDataSourceFactory(if (useBandwidthMeter) bandwidthMeter else null)
    }

    private fun buildDataSourceFactory(bandwidthMeter: DefaultBandwidthMeter?): DataSource.Factory {
        return DefaultDataSourceFactory(
                context, bandwidthMeter,
                buildHttpDataSourceFactory(bandwidthMeter)
        )
    }

    private fun buildHttpDataSourceFactory(bandwidthMeter: DefaultBandwidthMeter?): HttpDataSource.Factory {
        return DefaultHttpDataSourceFactory("exoplayer-codelab", bandwidthMeter)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: PopularSeriesResult) =
                VideoFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("seriesData", param1)
                    }
                }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtDislike -> {

            }
            R.id.txtLike -> {

            }
            R.id.btnBottomDetail -> {
                detailActivityIntent.putExtra("seriesID", param1.id)
                startActivity(detailActivityIntent)
            }
        }
    }
}
