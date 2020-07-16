package com.example.weverse_shop_clone.data.source.remote.mapper

import com.example.weverse_shop_clone.data.model.BaseModel
import com.example.weverse_shop_clone.data.source.remote.response.BaseResponse

interface BaseMapper<Remote : BaseResponse, Data : BaseModel> {
    fun mapToRemote(from: Data): Remote
    fun mapToData(from: Remote): Data
}