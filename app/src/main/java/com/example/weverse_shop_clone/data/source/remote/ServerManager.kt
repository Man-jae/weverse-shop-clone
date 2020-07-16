package com.example.weverse_shop_clone.data.source.remote

import com.example.weverse_shop_clone.data.source.remote.response.InfoResponse
import retrofit2.Response

class ServerManager {
    companion object {
        private val SERVER = ServerDefine.retrofit.create(ServerInterface::class.java)

        suspend fun getInfo(artistId: Int): Response<InfoResponse> {
            return SERVER.requestInfo(artistId)
        }
    }
}