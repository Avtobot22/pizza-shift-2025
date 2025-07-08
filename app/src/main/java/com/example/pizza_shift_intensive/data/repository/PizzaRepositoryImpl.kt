package com.example.pizza_shift_intensive.data.repository

import com.example.pizza_shift_intensive.data.api.PizzaApi
import com.example.pizza_shift_intensive.data.converter.PizzaCatalogConverter
import com.example.pizza_shift_intensive.domain.model.PizzaModel


class PizzaRepositoryImpl(
    private val pizzaApi: PizzaApi,
    private val pizzaCatalogConverter: PizzaCatalogConverter
) : PizzaRepository {

    override suspend fun getPizzas(): List<PizzaModel> {
        return try {
            val response = pizzaApi.getPizzas()
            pizzaCatalogConverter.convert(response)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getPizzaDetails(pizzaId: String): PizzaModel? {
        return try {
            val response = pizzaApi.getPizzas()
            pizzaCatalogConverter.convert(response)
                .firstOrNull { it.id == pizzaId }
        } catch (e: Exception) {
            null
        }
    }
}