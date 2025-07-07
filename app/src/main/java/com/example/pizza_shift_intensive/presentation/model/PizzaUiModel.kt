package com.example.pizza_shift_intensive.presentation.model

import com.example.pizza_shift_intensive.domain.model.ComponentModel
import com.example.pizza_shift_intensive.domain.model.DoughOptionModel
import com.example.pizza_shift_intensive.domain.model.SizeOptionModel

data class PizzaUiModel(
    val id: String,
    val name: String,
    val description: String,
    val img: String,
    val price: Int,
)
