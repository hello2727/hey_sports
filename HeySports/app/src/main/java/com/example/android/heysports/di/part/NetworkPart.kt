package com.example.android.heysports.di.part

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Jihye Noh
 * Date: 2022-02-17
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkPart {
    @Provides
    @Singleton
    fun getRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(YOUTUBE_API)
        .addConverterFactory(getGson())
        .build()

    @Provides
    @Singleton
    fun getGson(): GsonConverterFactory = GsonConverterFactory.create()
}

private val YOUTUBE_API =
    "https://www.googleapis.com/youtube/v3/videos?id=7lCDEYXw3mM&key=AIzaSyDGqNg6oN-YBQr2DKAX1j4Om9L5i5r1Xvk&part=snippet,contentDetails,statistics,status"