package com.example.weverse_shop_clone.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShopSaleResponse(
    @Expose
    @SerializedName("id")
    val id: Int = 0,

    @Expose
    @SerializedName("name")
    val name: String = "",

    @Expose
    @SerializedName("imageUrl")
    val imageUrl: String = "",

    @Expose
    @SerializedName("isPreOrder")
    val isPreOrder: Boolean = false,

    @Expose
    @SerializedName("isSoldOut")
    val isSoldOut: Boolean = false,

    @Expose
    @SerializedName("isMonopoly")
    val isMonopoly: Boolean = false,

    @Expose
    @SerializedName("isFcOnly")
    val isFcOnly: Boolean = false,

    @Expose
    @SerializedName("originalPrice")
    val originalPrice: Double = 0.0,

    @Expose
    @SerializedName("salePrice")
    val salePrice: Double? = null
) : BaseResponse