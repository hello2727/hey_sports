package com.example.android.heysports.util.player.listener

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import timber.log.Timber

/**
 * Created by Jihye Noh
 * Date: 2022-02-26
 */
object PlaybackStateListener : Player.Listener {
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