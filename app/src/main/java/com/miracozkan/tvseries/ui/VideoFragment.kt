package com.miracozkan.tvseries.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.SmallImageAdapter
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.datalayer.model.VideoFragmentModel
import com.miracozkan.tvseries.datalayer.network.RetrofitClient
import com.miracozkan.tvseries.utils.DependencyUtil
import com.miracozkan.tvseries.viewmodel.VideoViewModel
import com.miracozkan.tvseries.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.layout_video.*


class VideoFragment : Fragment() {

    private lateinit var param1: PopularSeriesResult

    var exoPlayer: SimpleExoPlayer? = null
    lateinit var mediaDataSourceFactory: DataSource.Factory
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
        Log.e("VideoID", param1.id.toString())
        return inflater.inflate(R.layout.fragment_video, container, false)

    }

    @SuppressLint("StaticFieldLeak")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bandwidthMeter = DefaultBandwidthMeter()
        mediaDataSourceFactory = buildDataSourceFactory(true)

        if (exoPlayer == null)
            exoPlayer = ExoPlayerFactory.newSimpleInstance(
                activity,
                DefaultTrackSelector(), DefaultLoadControl()
            )

        simpleExoPlayerView.apply {
            player = exoPlayer
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        }

        txtTitle.text = param1.originalName
        txtLike.text = param1.voteCount.toString()
        txtTomato.text = param1.voteAverage.toString()

        with(recycImages) {
            adapter = SmallImageAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        /**
         * Network Req
         */

        if (!videoViewModel.seriesVideo.isNullOrEmpty()) {
            exoPlayer?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            object : YouTubeExtractor(context!!) {
                override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta) {
                    val iTag = 18// 135 -> 480p, 18 -> 360p, 17 -> 144p
                    if (ytFiles?.get(iTag) != null && ytFiles.get(iTag).url != null) {
                        val downloadUrl = ytFiles.get(iTag).url
                        val extractorsFactory = DefaultExtractorsFactory()
                        val mediaSource = ExtractorMediaSource.Factory(mediaDataSourceFactory)
                            .setExtractorsFactory(extractorsFactory)
                            .createMediaSource(Uri.parse(downloadUrl))
                        exoPlayer?.prepare(mediaSource, true, false)
                        exoPlayer?.seekTo(5000)
                    } else {
                        val toast =
                            Toast.makeText(context, "Video Başlatılamadı Tekrar Deneyin", Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
            }.extract("http://youtube.com/watch?v=${videoViewModel.seriesVideo.first()[0].key}", true, true)
        }


        if (videoViewModel.seriesImage.isNullOrEmpty()) {
            Log.e("VideoFragmant", param1.originalName!!)
        } else {
            (recycImages.adapter as SmallImageAdapter).setNewItem(videoViewModel.seriesImage.first())
        }

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
        pausePlayer()
    }

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
}
