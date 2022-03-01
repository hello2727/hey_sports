package com.example.android.heysports.di.part

import com.example.android.heysports.HeyApplication
import com.example.android.heysports.R
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
        .baseUrl(HeyApplication.getContext().getString(R.string.youtube_api))
        .addConverterFactory(getGson())
        .build()

    @Provides
    @Singleton
    fun getGson(): GsonConverterFactory = GsonConverterFactory.create()
}