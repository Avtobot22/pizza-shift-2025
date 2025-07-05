package com.example.pizza_shift_intensive.presentation.mappers

import com.example.pizza_shift_intensive.domain.model.PizzaModel
import com.example.pizza_shift_intensive.presentation.model.PizzaUiModel

fun PizzaModel.toUiModel(price: Int): PizzaUiModel {
    return PizzaUiModel(
        id = this.id,
        name = this.name,
        ingredients = this.ingredients,
        toppings = this.toppings,
        description = this.description,
        sizes = this.sizes,
        doughs = this.doughs,
        calories = this.calories,
        protein = this.protein,
        totalFat = this.totalFat,
        carbohydrates = this.carbohydrates,
        sodium = this.sodium,
        allergens = this.allergens,
        isVegetarian = this.isVegetarian,
        isGlutenFree = this.isGlutenFree,
        isNew = this.isNew,
        isHit = this.isHit,
        img = this.img,
        price = price
    )
}