package com.miracozkan.tvseries.ui.series.search.item


import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
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
import com.google.android.material.snackbar.Snackbar
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.adapter.VideoPosterAdapter
import com.miracozkan.tvseries.base.BaseFragment
import com.miracozkan.tvseries.databinding.BottomSheetVideoBinding
import com.miracozkan.tvseries.databinding.FragmentSeriesItemBinding
import com.miracozkan.tvseries.datalayer.model.PopularSeriesResult
import com.miracozkan.tvseries.utils.Status.*
import com.miracozkan.tvseries.utils.extensions.injectViewModel
import com.miracozkan.tvseries.utils.hideProgress
import com.miracozkan.tvseries.utils.showProgress
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.layout_video.*

/**
 * A simple [Fragment] subclass.
 */
class SeriesItemFragment : BaseFragment() {

    private lateinit var binding: FragmentSeriesItemBinding
    private lateinit var bottomBinding: BottomSheetVideoBinding
    private lateinit var seriesData: PopularSeriesResult
    private lateinit var viewModel: SeriesItemViewModel

    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    private lateinit var bandwidthMeter: DefaultBandwidthMeter

    private lateinit var adapter: VideoPosterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentSeriesItemBinding.inflate(inflater, null, false)
        seriesData = arguments?.getParcelable("seriesData")!!
        viewModel.setSeriesId(seriesData.id ?: 1)
        bottomBinding = BottomSheetVideoBinding.inflate(inflater, null, false)
        adapter = VideoPosterAdapter {
            Log.e("Clicked -> ", it.toString())
        }.also {
            binding.layoutVideo.adapter = it
        }

        binding.btmLayout.btnBottomDetail.setOnClickListener {
            val bundle = bundleOf("seriesId" to seriesData.id)
            findNavController()
                .navigate(
                    R.id.seriesDetailFragment,
                    bundle,
                    NavOptions.Builder()
                        .build()
                )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initExoPlayer()

        viewModel.seriesImages.observe(viewLifecycleOwner, Observer { imageResult ->
            when (imageResult.status) {
                SUCCESS -> {
                    binding.prgBar.hideProgress()
                    imageResult.data?.let {
                        adapter.setNewItem(it)
                    }
                }
                ERROR -> {
                    binding.prgBar.hideProgress()
                    Snackbar.make(view, imageResult.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()

                }
                LOADING -> {
                    binding.prgBar.showProgress()
                }
            }
        })

        viewModel.seriesVideo.observe(viewLifecycleOwner, Observer { videoResult ->
            when (videoResult.status) {
                SUCCESS -> {
                    binding.prgBar.hideProgress()
                    videoResult.data?.let {
                        val videoKey = if (it.isNullOrEmpty().not()) {
                            it.last().key ?: "iwNp2E1aV3Q"
                        } else {
                            "iwNp2E1aV3Q"
                        }
                        releaseExo(videoKey)
                    }
                }
                ERROR -> {
                    binding.prgBar.hideProgress()
                    Snackbar.make(view, videoResult.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
                LOADING -> {
                    binding.prgBar.showProgress()
                }
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
            }
        }.extract("http://youtube.com/watch?v=$url", true, true)
    }

    /**
     * Initialize Exo
     */

    private fun initExoPlayer() {
        bandwidthMeter = DefaultBandwidthMeter()
        mediaDataSourceFactory = buildDataSourceFactory(true)

        if (exoPlayer == null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(
                activity,
                DefaultTrackSelector(),
                DefaultLoadControl()
            )
        }

        pvExoPlayer.apply {
            player = exoPlayer
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        }
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

    /**
     * Lifecycle Events
     */

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
     * Exo Start-Release-Pause
     */

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            startPlayer()
        } else {
            pausePlayer()
        }
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

}
