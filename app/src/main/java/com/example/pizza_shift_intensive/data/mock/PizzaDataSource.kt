package com.example.pizza_shift_intensive.data.mock

import com.example.pizza_shift_intensive.domain.model.PizzaModel

interface PizzaDataSource {
    suspend fun getPizzas(): List<PizzaModel>
    fun getPizzaDetails(pizzaId: String): PizzaModel?
}