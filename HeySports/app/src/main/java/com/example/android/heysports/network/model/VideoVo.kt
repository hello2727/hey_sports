package com.example.android.heysports.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Jihye Noh
 * Date: 2022-03-01
 */
data class VideoVo(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("etag")
    val etag: String,

    @SerializedName("nextPageToken")
    val nextPageToken: String,

    @SerializedName("prevPageToken")
    val prevPageToken: String,

    @SerializedName("pageInfo")
    val pageInfo: List<ResultVo>,

    @SerializedName("items")
    val items: List<String>
)

data class ResultVo(
    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("resultsPerPage")
    val resultsPerPage: Int
)
