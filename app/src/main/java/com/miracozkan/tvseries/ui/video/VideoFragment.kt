package com.miracozkan.tvseries.ui.video

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
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
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.utils.Status
import com.miracozkan.tvseries.utils.dissappearProgress
import com.miracozkan.tvseries.utils.extensions.injectViewModel
import com.miracozkan.tvseries.utils.hideProgress
import com.miracozkan.tvseries.utils.showProgress
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottom_sheet_video.*
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.layout_video.*


class VideoFragment : BaseFragment(), View.OnClickListener {

    private lateinit var param1: PopularSeriesResult
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    private lateinit var bandwidthMeter: DefaultBandwidthMeter


    private lateinit var videoViewModel: VideoViewModel

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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        videoViewModel = injectViewModel(viewModelFactory)
        videoViewModel.setSeriesId(param1.id.toString())
        btnBottomDetail.setOnClickListener(this)
        val bottomSheet = BottomSheetBehavior.from(lytBottomSheet)

        bottomSheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {//Acıldı
                        recycImages.dissappearProgress()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {//Kapandı
                        recycImages.showProgress()
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.e("state", "onSlide")
            }
        })

        txtDislike.setOnClickListener(this)
        txtLike.setOnClickListener(this)

        initExo()
        initUi()
        /**
         * Network Req
         */

        initSeriesVidoeObserver()
        initSeriesImageObserver()
    }

    private fun initExo() {
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
    }

    private fun initUi() {

        txtStoryLineDescBottom.text = param1.overview
        if (param1.originCountry.isNullOrEmpty()) {
            txtBottomCountry.text = "Gone"
        } else {
            txtBottomCountry.text = param1.originCountry?.first()
        }
        txtBottomDate.text = param1.firstAirDate
        txtBottomLang.text = param1.originalLanguage
        txtBottomName.text = param1.originalName

        txtTitle.text = param1.originalName
        txtLike.text = param1.voteCount.toString()
        txtDislike.text = param1.voteAverage.toString()

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
    }

    private fun initSeriesVidoeObserver() {
        videoViewModel.seriesVideo.observe(viewLifecycleOwner, Observer { _resource ->
            when (_resource.status) {
                Status.LOADING -> {
                    prgBar.showProgress()
                }
                Status.ERROR -> {
                    prgBar.hideProgress()
                    Toast.makeText(activity, _resource.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    if (!_resource.data.isNullOrEmpty()) {
                        releaseExo(_resource.data.last().key!!)
                        prgBar.hideProgress()
                    } else {
                        prgBar.hideProgress()
                        releaseExo("iwNp2E1aV3Q")
                        Toast.makeText(
                            activity,
                            "This TV Series Has Not Trailer",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }

    private fun initSeriesImageObserver() {
        videoViewModel.seriesImages.observe(viewLifecycleOwner, Observer { _resource ->
            when (_resource.status) {
                Status.LOADING -> {
                    prgBar.showProgress()
                }
                Status.ERROR -> {
                    prgBar.hideProgress()
                    Toast.makeText(activity, _resource.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    if (!_resource.data.isNullOrEmpty()) {
                        (recycImages.adapter as VideoPosterAdapter).setNewItem(_resource.data)
                        shimmer_view_container.stopShimmerAnimation()
                        shimmer_view_container.visibility = View.GONE
                        prgBar.hideProgress()
                    }
                }
            }
        })
    }

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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtDislike -> {

            }
            R.id.txtLike -> {

            }
            R.id.btnBottomDetail -> {

            }
        }
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
}
