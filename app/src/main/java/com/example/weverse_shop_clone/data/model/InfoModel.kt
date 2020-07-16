package com.example.weverse_shop_clone.data.model

data class InfoModel(
    val artists: ArrayList<ArtistModel>,
    val banners: ArrayList<BannerModel>,
    val shops: ArrayList<ShopModel>,
    val notices: ArrayList<NoticeModel>
) : BaseModel