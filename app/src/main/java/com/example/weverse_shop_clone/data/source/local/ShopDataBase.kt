package com.example.weverse_shop_clone.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import java.util.*

@Entity(primaryKeys = ["id", "artistId"])
@TypeConverters(DateConverter::class)
data class ShopDataBase(
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "artistId") var artistId: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "createAt") val createAt: Date
) : BaseRoom