package com.example.pizza_shift_intensive.domain.usecase

import com.example.pizza_shift_intensive.data.repository.PizzaRepository

class GetPizzaDetailsUseCase(
    private val getInitialPizzaPriceUseCase: GetInitialPizzaPriceUseCase = GetInitialPizzaPriceUseCase(),
    private val pizzaRepository: PizzaRepository
) {
    suspend operator fun invoke(pizzaId: String): PizzaDetailsResult {
        val pizza = pizzaRepository.getPizzaDetails(pizzaId)
        if (pizza == null) {
            return PizzaDetailsResult.NotFound
        }
        val price = getInitialPizzaPriceUseCase(pizza)
        return PizzaDetailsResult.Success(pizza, price)
    }
}