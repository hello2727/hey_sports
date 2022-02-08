package com.example.android.heysports.util.extension

import android.content.Context
import com.example.android.heysports.network.HOME_INTRODUCTION_VIDEO_URL
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

/**
 * Created by Jihye Noh
 * Date: 2022-02-07
 */
fun StyledPlayerView?.setPlayer(context: Context?) {
    ExoPlayer.Builder(context ?: return)
        .build()
        .also { exoPlayer ->
            this?.player = exoPlayer

            val mediaItem = MediaItem.fromUri(HOME_INTRODUCTION_VIDEO_URL)
            exoPlayer.setMediaItem(mediaItem)
        }
}