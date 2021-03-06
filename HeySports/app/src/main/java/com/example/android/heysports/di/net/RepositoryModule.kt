package com.example.android.heysports.di.net

import com.example.android.heysports.domain.net.YoutubeSearchRepository
import com.example.android.heysports.network.services.YoutubeSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Jihye Noh
 * Date: 2022-03-05
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideYoutubeSearchRepository(service: YoutubeSearchService) =
        YoutubeSearchRepository(service)
}