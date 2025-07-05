package com.example.pizza_shift_intensive.domain.model

data class CatalogResponseModel(
    val success: Boolean,
    val reason: String,
    val catalog: List<PizzaModel>
)