package com.example.weverse_shop_clone.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopDao {
    @Query("SELECT * FROM ShopDataBase WHERE artistId = :artistId")
    fun read(artistId: Int): List<ShopDataBase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(shop: ShopDataBase)
}