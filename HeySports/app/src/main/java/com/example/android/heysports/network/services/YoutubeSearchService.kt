package com.example.android.heysports.network.services

import com.example.android.heysports.HeyApplication
import com.example.android.heysports.R
import com.example.android.heysports.network.model.SearchVo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Jihye Noh
 * Date: 2022-03-02
 */
interface YoutubeSearchService {
    @GET("search")
    suspend fun getSearchVideoList(
        @Query("key") key: String = HeyApplication.getContext().getString(R.string.youtube_key),
        @Query("id") id: String = HeyApplication.getContext()
            .getString(R.string.youtube_channel_olympics_id)
    ): Response<SearchVo?>
}