package com.example.pizza_shift_intensive.presentation.mappers

import com.example.pizza_shift_intensive.domain.model.ComponentModel
import com.example.pizza_shift_intensive.domain.model.PizzaModel
import com.example.pizza_shift_intensive.presentation.model.PizzaDetailsUiModel

fun PizzaModel.toDetailsUiModel(price: Int): PizzaDetailsUiModel {
    return PizzaDetailsUiModel(
        id = this.id,
        name = this.name,
        description = this.description,
        ingredients = formatterIngredients(this.ingredients),
        toppings = this.toppings.map { it.toUiModel(::formatFirstLetter) },
        doughs = this.doughs.map { it.toUiModel() },
        sizes = this.sizes.map { it.toUiModel() },
        img = this.img,
        price = price,
        selectedToppings = emptyList(),
        selectedDough = this.doughs.first().toUiModel(),
        selectedSize = this.sizes.first().toUiModel(),
    )
}

private fun formatterIngredients(ingredients: List<ComponentModel>): String {
    return ingredients.joinToString(separator = ", ") { typeToString(it.type) }
        .replaceFirstChar { it.uppercase() }
}