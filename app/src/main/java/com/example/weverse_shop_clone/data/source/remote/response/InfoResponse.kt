package com.example.weverse_shop_clone.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class InfoResponse(
    @Expose
    @SerializedName("artists")
    val artists: ArrayList<ArtistResponse> = arrayListOf(),

    @Expose
    @SerializedName("banners")
    val banners: ArrayList<BannerResponse> = arrayListOf(),

    @Expose
    @SerializedName("shop")
    val shops: ArrayList<ShopResponse> = arrayListOf(),

    @Expose
    @SerializedName("notices")
    val notices: ArrayList<NoticeResponse> = arrayListOf()
) : BaseResponse
