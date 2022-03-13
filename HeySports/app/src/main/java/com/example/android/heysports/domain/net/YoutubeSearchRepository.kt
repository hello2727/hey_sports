package com.example.android.heysports.domain.net

import com.example.android.heysports.network.services.YoutubeSearchService

/**
 * Created by Jihye Noh
 * Date: 2022-03-05
 */
class YoutubeSearchRepository(private val service: YoutubeSearchService) {
    suspend fun getSearchVideoList(q: String) = service.getSearchVideoList(q = q).body()
}