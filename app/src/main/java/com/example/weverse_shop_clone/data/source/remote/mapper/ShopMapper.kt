package com.example.weverse_shop_clone.data.source.remote.mapper

import com.example.weverse_shop_clone.data.model.ShopModel
import com.example.weverse_shop_clone.data.source.remote.response.ShopResponse
import com.example.weverse_shop_clone.data.source.remote.response.ShopSaleResponse

object ShopMapper : BaseMapper<ShopResponse, ShopModel> {
    override fun mapToRemote(from: ShopModel): ShopResponse {
        return ShopResponse(
            category = from.category.let(ShopCategoryMapper::mapToRemote),
            sales = from.sales.map(ShopSaleMapper::mapToRemote) as ArrayList<ShopSaleResponse>
        )
    }

    override fun mapToData(from: ShopResponse): ShopModel {
        return ShopModel(
            category = from.category.let(ShopCategoryMapper::mapToData),
            sales = from.sales.map(ShopSaleMapper::mapToData) as ArrayList<ShopModel.ShopSaleModel>
        )
    }
}