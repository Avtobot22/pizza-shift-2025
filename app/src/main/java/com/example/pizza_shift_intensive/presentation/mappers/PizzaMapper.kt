package com.example.pizza_shift_intensive.presentation.mappers

import com.example.pizza_shift_intensive.domain.model.PizzaModel
import com.example.pizza_shift_intensive.presentation.model.PizzaUiModel

fun PizzaModel.toUiModel(price: Int): PizzaUiModel {
    return PizzaUiModel(
        id = this.id,
        name = this.name,
        description = this.description,
        img = this.img,
        price = price
    )
}