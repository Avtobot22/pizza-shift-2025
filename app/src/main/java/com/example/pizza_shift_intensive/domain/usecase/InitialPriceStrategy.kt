package com.example.pizza_shift_intensive.domain.usecase

import com.example.pizza_shift_intensive.domain.model.PizzaModel
import com.example.pizza_shift_intensive.domain.model.TypeSizeModel

fun interface InitialPriceStrategy {
    fun findInitialPrice(pizza: PizzaModel): Int?
}

class SmallestSizePriceStrategy : InitialPriceStrategy {
    override fun findInitialPrice(pizza: PizzaModel): Int? {
        return pizza.sizes.find { it.type == TypeSizeModel.SMALL }?.price
    }
}

class FirstAvailablePriceStrategy : InitialPriceStrategy {
    override fun findInitialPrice(pizza: PizzaModel): Int? {
        return pizza.sizes.firstOrNull()?.price
    }
}

