package com.example.android.heysports.util.player

import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
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
    }

    private fun initializePlayer() {

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