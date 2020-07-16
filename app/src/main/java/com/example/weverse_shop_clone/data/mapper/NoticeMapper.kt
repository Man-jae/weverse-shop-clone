package com.example.weverse_shop_clone.data.mapper

import com.example.weverse_shop_clone.data.model.NoticeModel
import com.example.weverse_shop_clone.data.source.remote.response.NoticeResponse

object NoticeMapper : BaseMapper<NoticeResponse, NoticeModel> {
    override fun mapToRemote(from: NoticeModel): NoticeResponse {
        return NoticeResponse(
            id = from.id,
            title = from.title,
            date = from.date,
            isNew = from.isNew
        )
    }

    override fun mapToData(from: NoticeResponse): NoticeModel {
        return NoticeModel(
            id = from.id,
            title = from.title,
            date = from.date,
            isNew = from.isNew
        )
    }
}