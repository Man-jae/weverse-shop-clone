package com.example.weverse_shop_clone.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShopResponse(
    @Expose
    @SerializedName("category")
    val category: ShopCategoryResponse = ShopCategoryResponse(),

    @Expose
    @SerializedName("sales")
    val sales: ArrayList<ShopSaleResponse> = arrayListOf()
) : BaseResponse
