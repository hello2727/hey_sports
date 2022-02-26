package com.example.android.heysports.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.heysports.R
import com.example.android.heysports.databinding.FragmentHomeBinding
import com.example.android.heysports.util.extension.initPlayer
import com.example.android.heysports.util.extension.releasePlayer
import com.example.android.heysports.util.player.listener.PlaybackStateListener
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jihye Noh
 * Date: 2022-02-05
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val playbackStateListener: Player.Listener by lazy {
        PlaybackStateListener
    }
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

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
        binding.videoView.initPlayer(
            context,
            currentWindow,
            playbackPosition,
            playbackStateListener
        )
    }

    override fun onPause() {
        super.onPause()
        binding.videoView.releasePlayer(playbackStateListener)
        binding.videoView.player?.pause()
    }

    override fun onStop() {
        super.onStop()
        binding.videoView.releasePlayer(playbackStateListener)
        with(binding.videoView.player) {
            playbackPosition = this?.currentPosition ?: 0L
            currentWindow = this?.currentMediaItemIndex ?: 0
            playWhenReady = this?.playWhenReady ?: true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoView.player = null
        _binding = null
    }
}