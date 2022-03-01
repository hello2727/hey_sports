package com.example.android.heysports

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Jihye Noh
 * Date: 2022-02-05
 */
@HiltAndroidApp
class HeyApplication : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: HeyApplication
        fun getContext(): Context = instance.applicationContext
    }
}