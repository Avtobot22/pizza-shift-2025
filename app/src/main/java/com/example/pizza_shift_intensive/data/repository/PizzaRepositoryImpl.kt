package com.example.pizza_shift_intensive.data.repository

import com.example.pizza_shift_intensive.data.api.PizzaApi
import com.example.pizza_shift_intensive.data.converter.PizzaCatalogConverter
import com.example.pizza_shift_intensive.domain.model.PizzaModel


class PizzaRepositoryImpl(
    private val pizzaApi: PizzaApi,
    private val pizzaCatalogConverter: PizzaCatalogConverter
) : PizzaRepository {

    override suspend fun getPizzas(): List<PizzaModel> {
        val response = pizzaApi.getPizzas()
        return pizzaCatalogConverter.convert(response)
    }

    override suspend fun getPizzaDetails(pizzaId: String): PizzaModel? {
        val response = pizzaApi.getPizzas()
        return pizzaCatalogConverter.convert(response)
            .firstOrNull { it.id == pizzaId }
    }
}