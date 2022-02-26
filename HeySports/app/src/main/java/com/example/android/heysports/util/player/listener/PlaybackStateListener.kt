package com.example.android.heysports.util.player.listener

import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException
import timber.log.Timber

/**
 * Created by Jihye Noh
 * Date: 2022-02-26
 */
object PlaybackStateListener : Player.Listener {
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        if (isPlaying) {
            Timber.d("home video: playing!!")
        } else {
            Timber.d("home video: stop~")
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        val cause: Throwable = error.cause ?: return
        if (cause is HttpDataSourceException) {
            val httpError = cause as? HttpDataSourceException ?: return
            val requestDataSpec: DataSpec = httpError.dataSpec

            if (httpError is HttpDataSource.InvalidResponseCodeException) {
                // Cast to InvalidResponseCodeException and retrieve the response code,
                // message and headers.
            } else {
                // Try calling httpError.getCause() to retrieve the underlying cause,
                // although note that it may be null.
            }
        }
    }
}