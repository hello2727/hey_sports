package com.example.android.heysports.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Jihye Noh
 * Date: 2022-03-01
 */
data class SearchVo(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("etag")
    val etag: String,

    @SerializedName("nextPageToken")
    val nextPageToken: String,

    @SerializedName("regionCode")
    val regionCode: String,

    @SerializedName("pageInfo")
    val pageInfo: PageInfoVo,

    @SerializedName("items")
    val items: List<ItemVo>
)

data class PageInfoVo(
    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("resultsPerPage")
    val resultsPerPage: Int
)

data class ItemVo(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("etag")
    val etag: String,

    @SerializedName("id")
    val id: IdVo
)

data class IdVo(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("videoId")
    val videoId: String?,

    @SerializedName("channelId")
    val channelId: String?
)
