package com.example.android.heysports.util.player

import android.content.Context

/**
 * Created by Jihye Noh
 * Date: 2022-02-13
 */
class ExoPlayer @JvmOverloads constructor(
    context: Context
) {
    var mInstance: ExoPlayer? = null

    fun getSharedInstance(context: Context): ExoPlayer? {
        if (mInstance == null) {
            mInstance = ExoPlayer(context)
        }
        return mInstance
    }
}