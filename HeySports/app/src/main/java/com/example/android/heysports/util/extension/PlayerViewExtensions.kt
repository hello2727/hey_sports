package com.example.android.heysports.util.extension

import android.content.Context
import com.example.android.heysports.R
import com.example.android.heysports.network.HOME_INTRODUCTION_VIDEO_URL
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.MimeTypes

/**
 * Created by Jihye Noh
 * Date: 2022-02-07
 */
fun StyledPlayerView?.initPlayer(
    context: Context?,
    currentWindow: Int,
    playbackPosition: Long,
    playbackStateListener: Player.Listener
) {
    val trackSelector = DefaultTrackSelector(context ?: return).apply {
        setParameters(buildUponParameters().setMaxVideoSizeSd())
    }

    ExoPlayer.Builder(context)
        .setTrackSelector(trackSelector)
        .build()
        .also { exoPlayer ->
            this?.player = exoPlayer

            val mediaItem = MediaItem.Builder()
                .setUri(context.getString(R.string.media_url_dash))
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

fun StyledPlayerView?.releasePlayer(
    playbackStateListener: Player.Listener
) {
    this?.player?.run {
        removeListener(playbackStateListener)
        release()
    }
}