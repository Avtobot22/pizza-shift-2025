package com.example.pizza_shift_intensive.data.repository

import com.example.pizza_shift_intensive.domain.model.PizzaModel


interface PizzaRepository {
    suspend fun getPizzas(): List<PizzaModel>
    suspend fun getPizzaDetails(pizzaId: String): PizzaModel?
}