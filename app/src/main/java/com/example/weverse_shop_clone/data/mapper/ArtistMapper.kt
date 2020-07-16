package com.example.weverse_shop_clone.data.mapper

import com.example.weverse_shop_clone.data.model.ArtistModel
import com.example.weverse_shop_clone.data.source.remote.response.ArtistResponse

object ArtistMapper : BaseMapper<ArtistResponse, ArtistModel> {
    override fun mapToRemote(from: ArtistModel): ArtistResponse {
        return ArtistResponse(
            id = from.id,
            name = from.name,
            imageUrl = from.imageUrl,
            shops = from.shops
        )
    }

    override fun mapToData(from: ArtistResponse): ArtistModel {
        return ArtistModel(
            id = from.id,
            name = from.name,
            imageUrl = from.imageUrl,
            shops = from.shops
        )
    }
}