package com.example.android.heysports.util.extension

import android.content.Context
import android.net.Uri
import com.example.android.heysports.util.player.listener.PlaybackStateListener
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource

/**
 * Created by Jihye Noh
 * Date: 2022-02-07
 */
fun ExoPlayer.initPlayer(context: Context, url: String) {
    val dataSourceFactory = DefaultDataSource.Factory(context)
    val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(MediaItem.fromUri(Uri.parse(url)))

    this.run {
        addListener(PlaybackStateListener)

        setMediaSource(mediaSource)
        prepare()
        play()
    }
}