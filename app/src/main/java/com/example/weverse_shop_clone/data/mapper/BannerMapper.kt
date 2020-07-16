package com.example.weverse_shop_clone.data.mapper

import com.example.weverse_shop_clone.data.model.BannerModel
import com.example.weverse_shop_clone.data.source.remote.response.BannerResponse

object BannerMapper: BaseMapper<BannerResponse, BannerModel> {
    override fun mapToRemote(from: BannerModel): BannerResponse {
        return BannerResponse(
            title = from.title,
            subTitle = from.subTitle,
            imageUrl = from.imageUrl
        )
    }

    override fun mapToData(from: BannerResponse): BannerModel {
        return BannerModel(
            title = from.title,
            subTitle = from.subTitle,
            imageUrl = from.imageUrl
        )
    }
}