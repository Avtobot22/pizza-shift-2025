package com.example.pizza_shift_intensive.domain.usecase

import com.example.pizza_shift_intensive.data.repository.PizzaRepository

class GetPizzasUseCase(private val pizzaRepository: PizzaRepository) {
    suspend operator fun invoke() = pizzaRepository.getPizzas()
}