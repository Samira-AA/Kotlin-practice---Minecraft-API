package com.example.prueba_parcial.network

import com.example.prueba_parcial.communication.ApiResponse
import com.example.prueba_parcial.models.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MinecraftApiService {
    @GET("api/items")
    fun getItems(@Query("limit") limit: Int): Call<List<Item>>
}
