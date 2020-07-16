package com.example.weverse_shop_clone.data.source.remote

import retrofit2.Response

class ServerManager {
    companion object {
        private val SERVER = ServerDefine.retrofit.create(ServerInterface::class.java)
    }
}