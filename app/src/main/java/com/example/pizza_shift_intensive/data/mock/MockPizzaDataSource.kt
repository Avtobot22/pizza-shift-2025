package com.example.pizza_shift_intensive.data.mock

import com.example.pizza_shift_intensive.domain.model.PizzaModel

class MockPizzaDataSource : PizzaDataSource {
    override suspend fun getPizzas(): List<PizzaModel> {
        return getPizzasList()
    }

    override fun getPizzaDetails(pizzaId: String): PizzaModel? {
        return getPizzaById(pizzaId)
    }
}