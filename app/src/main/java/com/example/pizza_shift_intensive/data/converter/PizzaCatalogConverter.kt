package com.example.pizza_shift_intensive.data.converter

import com.example.pizza_shift_intensive.data.api.mappers.formatImageUrl
import com.example.pizza_shift_intensive.data.api.mappers.toComponentModel
import com.example.pizza_shift_intensive.data.api.mappers.toDoughOptionModel
import com.example.pizza_shift_intensive.data.api.mappers.toSizeOptionModel
import com.example.pizza_shift_intensive.data.api.model.CatalogResponseModel
import com.example.pizza_shift_intensive.domain.model.PizzaModel

class PizzaCatalogConverter {
    fun convert(model: CatalogResponseModel): List<PizzaModel> {
        return model.catalog.map { pizzaApiModel ->
            PizzaModel(
                id = pizzaApiModel.id,
                name = pizzaApiModel.name,
                description = pizzaApiModel.description,
                ingredients = pizzaApiModel.ingredients.map { it.toComponentModel() },
                toppings = pizzaApiModel.toppings.map { it.toComponentModel() },
                sizes = pizzaApiModel.sizes.map { it.toSizeOptionModel() },
                doughs = pizzaApiModel.doughs.map { it.toDoughOptionModel() },
                calories = pizzaApiModel.calories,
                protein = pizzaApiModel.protein,
                totalFat = pizzaApiModel.totalFat,
                carbohydrates = pizzaApiModel.carbohydrates,
                sodium = pizzaApiModel.sodium,
                allergens = pizzaApiModel.allergens,
                isVegetarian = pizzaApiModel.isVegetarian,
                isHit = pizzaApiModel.isHit,
                isNew = pizzaApiModel.isNew,
                isGlutenFree = pizzaApiModel.isGlutenFree,
                img = formatImageUrl(pizzaApiModel.img),
            )
        }
    }
}