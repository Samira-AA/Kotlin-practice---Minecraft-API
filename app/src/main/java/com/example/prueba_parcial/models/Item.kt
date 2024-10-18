package com.example.prueba_parcial.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item (

    @PrimaryKey
    val id: Int? =null,

    @ColumnInfo(name="name")
    val name: String?,

    @ColumnInfo(name="description")
    val description: String?,

    @ColumnInfo(name = "image")
    val image: String?
)