package com.example.pizza_shift_intensive.data.api

import com.example.pizza_shift_intensive.data.api.model.CatalogResponseModel
import retrofit2.http.GET

fun interface PizzaApi {
    @GET("catalog")
    suspend fun getPizzas(): CatalogResponseModel
}