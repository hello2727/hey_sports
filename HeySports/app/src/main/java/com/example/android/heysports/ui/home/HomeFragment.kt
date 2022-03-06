package com.example.android.heysports.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.android.heysports.R
import com.example.android.heysports.databinding.FragmentHomeBinding
import com.example.android.heysports.network.HOME_INTRODUCTION_VIDEO_URL
import com.example.android.heysports.network.videoUrl
import com.example.android.heysports.util.extension.initPlayer
import com.google.android.exoplayer2.ExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Jihye Noh
 * Date: 2022-02-05
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(requireContext()).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentHomeBinding?>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            lifecycleOwner = this@HomeFragment
            model = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        extractYoutubeUrl()
        collectFlows()
    }

    override fun onPause() {
        super.onPause()
        binding.videoView.player?.pause()
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

        player.release()
    }

    @SuppressLint("StaticFieldLeak")
    private fun extractYoutubeUrl() {
        object : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                sparseArray: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                sparseArray?.let {
                    initPlayer(it[1].url)
                } ?: initPlayer()
            }
        }.extract(videoUrl, true, true)
    }

    private fun initPlayer(url: String = HOME_INTRODUCTION_VIDEO_URL) {
        binding.videoView.player = player
        player.initPlayer(requireContext(), url)
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            with(viewModel) {
                introductionVideo.collect {
                    Timber.d("searchList", it.toString())
                }
            }
        }
    }
}