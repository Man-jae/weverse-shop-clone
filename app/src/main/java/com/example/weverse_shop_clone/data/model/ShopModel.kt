package com.example.weverse_shop_clone.data.model

data class ShopModel(
    val category: ShopCategoryModel,
    val sales: ArrayList<ShopSaleModel>
) : BaseModel {
    data class ShopCategoryModel(
        val id: Int,
        val name: String
    ) : BaseModel

    data class ShopSaleModel(
        val id: Int,
        val name: String,
        val imageUrl: String,
        val isPreOrder: Boolean,
        val isSoldOut: Boolean,
        val isMonopoly: Boolean,
        val isFcOnly: Boolean,
        val originalPrice: Double,
        val salePrice: Double?
    ) : BaseModel
}