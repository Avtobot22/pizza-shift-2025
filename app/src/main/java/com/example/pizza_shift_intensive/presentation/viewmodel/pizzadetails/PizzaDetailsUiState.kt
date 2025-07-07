package com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails

import com.example.pizza_shift_intensive.presentation.model.PizzaDetailsUiModel

data class PizzaDetailsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val pizzaDetailsItem: PizzaDetailsUiModel? = null
)
