package com.example.pizza_shift_intensive.domain.usecase

import com.example.pizza_shift_intensive.domain.model.PizzaModel

sealed class PizzaDetailsResult {
    data class Success(val pizza: PizzaModel, val price: Int) : PizzaDetailsResult()
    object NotFound : PizzaDetailsResult()
}