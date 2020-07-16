package com.example.weverse_shop_clone.data.model

data class ArtistModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val shops: ArrayList<String>
) : BaseModel