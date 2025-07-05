package com.example.pizza_shift_intensive.data.repository

import com.example.pizza_shift_intensive.data.mock.PizzaDataSource
import com.example.pizza_shift_intensive.domain.model.PizzaModel


class PizzaRepositoryImpl(
    private val pizzaDataSource: PizzaDataSource
) : PizzaRepository {

    override suspend fun getPizzas(): List<PizzaModel> {
        return pizzaDataSource.getPizzas()
    }

    override suspend fun getPizzaDetails(pizzaId: String): PizzaModel? {
        return pizzaDataSource.getPizzaDetails(pizzaId)
    }
}