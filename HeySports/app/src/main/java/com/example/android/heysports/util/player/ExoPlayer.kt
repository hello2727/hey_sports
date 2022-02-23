package com.example.android.heysports.util.player

import androidx.appcompat.app.AppCompatActivity
import com.example.android.heysports.network.HOME_INTRODUCTION_VIDEO_URL
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import timber.log.Timber

/**
 * Created by Jihye Noh
 * Date: 2022-02-13
 */
class ExoPlayer : AppCompatActivity() {
    private val playbackStateListener: Player.Listener = playbackStateListener()
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    private fun initializePlayer() {
        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = ExoPlayer.Builder(this)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->

                val mediaItem = MediaItem.Builder()
                    .setUri(HOME_INTRODUCTION_VIDEO_URL)
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()
            }
    }
}

private fun playbackStateListener() = object : Player.Listener {
    override fun onPlaybackStateChanged(playbackState: Int) {
        val stateString: String = when (playbackState) {
            ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE  -"
            ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING  -"
            ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY  -"
            ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED  -"
            else -> "UNKNOWN_STATE"
        }
        Timber.d("home video: changed state to $stateString")
    }
}