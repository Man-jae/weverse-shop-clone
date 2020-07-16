package com.example.weverse_shop_clone.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @Expose
    @SerializedName("title")
    val title: String = "",

    @Expose
    @SerializedName("subTitle")
    val subTitle: String = "",

    @Expose
    @SerializedName("imageUrl")
    val imageUrl: String = ""
) : BaseResponse
