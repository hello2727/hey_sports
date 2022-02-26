package com.example.android.heysports.util.player

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android.heysports.network.HOME_INTRODUCTION_VIDEO_URL
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import timber.log.Timber

/**
 * Created by Jihye Noh
 * Date: 2022-02-13
 */
class ExoPlayer(
    private val adapter : StyledPlayerView
) : AppCompatActivity() {
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

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if(Util.SDK_INT <= 23 || player == null){
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if(Util.SDK_INT <= 23){
            releasePlayer()
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
                adapter.player = exoPlayer

                val mediaItem = MediaItem.Builder()
                    .setUri(HOME_INTRODUCTION_VIDEO_URL)
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()
                exoPlayer.apply {
                    setMediaItem(mediaItem)
                    playWhenReady = playWhenReady
                    seekTo(currentWindow, playbackPosition)
                    addListener(playbackStateListener)
                    prepare()
                }
            }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentMediaItemIndex
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)
            release()
        }
        player = null
    }

    @SuppressLint()
    private fun hideSystemUi(){
        adapter.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
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