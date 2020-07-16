package com.example.weverse_shop_clone.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NoticeResponse(
    @Expose
    @SerializedName("id")
    val id: Int = 0,

    @Expose
    @SerializedName("title")
    val title: String = "",

    @Expose
    @SerializedName("date")
    val date: String = "",

    @Expose
    @SerializedName("isNew")
    val isNew: Boolean = false
) : BaseResponse