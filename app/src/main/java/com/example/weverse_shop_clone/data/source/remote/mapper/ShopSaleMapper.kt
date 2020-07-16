package com.example.weverse_shop_clone.data.source.remote.mapper

import com.example.weverse_shop_clone.data.model.ShopModel
import com.example.weverse_shop_clone.data.source.remote.response.ShopSaleResponse

object ShopSaleMapper : BaseMapper<ShopSaleResponse, ShopModel.ShopSaleModel> {
    override fun mapToRemote(from: ShopModel.ShopSaleModel): ShopSaleResponse {
        return ShopSaleResponse(
            id = from.id,
            name = from.name,
            imageUrl = from.imageUrl,
            isPreOrder = from.isPreOrder,
            isSoldOut = from.isSoldOut,
            isMonopoly = from.isMonopoly,
            isFcOnly = from.isFcOnly,
            originalPrice = from.originalPrice,
            salePrice = from.salePrice
        )
    }

    override fun mapToData(from: ShopSaleResponse): ShopModel.ShopSaleModel {
        return ShopModel.ShopSaleModel(
            id = from.id,
            name = from.name,
            imageUrl = from.imageUrl,
            isPreOrder = from.isPreOrder,
            isSoldOut = from.isSoldOut,
            isMonopoly = from.isMonopoly,
            isFcOnly = from.isFcOnly,
            originalPrice = from.originalPrice,
            salePrice = from.salePrice
        )
    }
}