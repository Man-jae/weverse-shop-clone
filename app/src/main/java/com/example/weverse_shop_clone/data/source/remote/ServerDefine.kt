package com.example.weverse_shop_clone.data.source.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerDefine {
    companion object {
        private const val BASE_URL = "https://us-central1-android-home-work-echo-server.cloudfunctions.net/api/"

        val retrofit: Retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor() as Interceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}