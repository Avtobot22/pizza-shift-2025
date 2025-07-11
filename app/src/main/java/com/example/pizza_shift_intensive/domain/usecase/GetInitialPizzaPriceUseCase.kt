package com.example.pizza_shift_intensive.domain.usecase

import com.example.pizza_shift_intensive.domain.model.PizzaModel

class GetInitialPizzaPriceUseCase(
    private val strategies: List<InitialPriceStrategy>
) {

    operator fun invoke(pizza: PizzaModel): Int {
        return strategies.firstNotNullOfOrNull { it.findInitialPrice(pizza) } ?: 0
    }
}