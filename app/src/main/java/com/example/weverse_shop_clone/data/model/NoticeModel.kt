package com.example.weverse_shop_clone.data.model

data class NoticeModel(
    val id: Int,
    val title: String,
    val date: String,
    val isNew: Boolean
) : BaseModel