package com.example.weverse_shop_clone.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShopCategoryResponse(
    @Expose
    @SerializedName("id")
    val id: Int = 0,

    @Expose
    @SerializedName("name")
    val name: String = ""
) : BaseResponse