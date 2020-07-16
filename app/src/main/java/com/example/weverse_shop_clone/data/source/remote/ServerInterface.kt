package com.example.weverse_shop_clone.data.source.remote

import com.example.weverse_shop_clone.data.source.remote.response.InfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerInterface {
    companion object {
        private const val ARTIST_ID = "artist_id"
        private const val SHOP = "shop/{$ARTIST_ID}"
    }

    @GET(SHOP)
    suspend fun requestInfo(
        @Path(ARTIST_ID) artistId: Int
    ): Response<InfoResponse>
}
