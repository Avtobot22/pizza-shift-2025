package com.example.pizza_shift_intensive.domain.usecase

import com.example.pizza_shift_intensive.domain.model.PizzaModel

class GetPizzasWithInitialPriceUseCase(
    private val getPizzasUseCase: GetPizzasUseCase,
    private val getInitialPizzaPriceUseCase: GetInitialPizzaPriceUseCase
) {
    suspend operator fun invoke(): List<Pair<PizzaModel, Int>> {
        val pizzas = getPizzasUseCase()
        return pizzas.map { pizza ->
            val price = getInitialPizzaPriceUseCase.execute(pizza)
            pizza to price
        }
    }
}