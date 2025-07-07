package com.example.pizza_shift_intensive.presentation.model


data class PizzaDetailsUiModel(
    val id: String,
    val name: String,
    val description: String,
    val ingredients: String,
    val toppings: List<ComponentUiModel>,
    val selectedToppings: List<ComponentUiModel>,
    val doughs: List<DoughUiModel>,
    val selectedDough: DoughUiModel,
    val sizes: List<SizesUiModel>,
    val selectedSize: SizesUiModel,
    val img: String,
    val price: Int,
)
