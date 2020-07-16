package com.example.weverse_shop_clone.data.mapper

import com.example.weverse_shop_clone.data.model.ShopModel
import com.example.weverse_shop_clone.data.source.remote.response.ShopCategoryResponse

object ShopCategoryMapper : BaseMapper<ShopCategoryResponse, ShopModel.ShopCategoryModel> {
    override fun mapToRemote(from: ShopModel.ShopCategoryModel): ShopCategoryResponse {
        return ShopCategoryResponse(
            id = from.id,
            name = from.name
        )
    }

    override fun mapToData(from: ShopCategoryResponse): ShopModel.ShopCategoryModel {
        return ShopModel.ShopCategoryModel(
            id = from.id,
            name = from.name
        )
    }
}