package com.example.prueba_parcial.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.prueba_parcial.models.Item


@Dao
interface ItemDao {

    @Insert
    fun insertOne(item:Item)

    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Delete
    fun delete(item: Item)
}