package com.example.prueba_parcial.communication

import com.example.prueba_parcial.models.Item

class ItemResponse(
    private var name:String,
    private var description: String,
    private var image:String
)
{
    fun toItem():Item{
        return Item(
            name=name,
            description = description,
            image = image
        )
    }
}