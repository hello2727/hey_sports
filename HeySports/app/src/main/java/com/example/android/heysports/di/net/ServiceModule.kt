package com.example.android.heysports.di.net

import com.example.android.heysports.network.services.YoutubeSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Jihye Noh
 * Date: 2022-02-27
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideYoutubeSearchService(@Named("YoutubeSearch") retrofit: Retrofit): YoutubeSearchService {
        return retrofit.create(YoutubeSearchService::class.java)
    }
}