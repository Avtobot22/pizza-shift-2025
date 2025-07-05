package com.example.pizza_shift_intensive.domain.usecase

import com.example.pizza_shift_intensive.domain.model.PizzaModel
import com.example.pizza_shift_intensive.domain.model.TypeSizeModel

class GetInitialPizzaPriceUseCase {

    fun execute(pizza: PizzaModel): Int {
        val initialPrice = pizza.sizes.find { it.type == TypeSizeModel.SMALL }?.price
        return initialPrice ?: pizza.sizes.firstOrNull()?.price ?: 0
    }
}