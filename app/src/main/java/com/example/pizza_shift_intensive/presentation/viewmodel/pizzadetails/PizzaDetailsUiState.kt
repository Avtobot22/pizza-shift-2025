package com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails

import com.example.pizza_shift_intensive.presentation.model.PizzaDetailsUiModel

sealed interface PizzaDetailsUiState{
    data object Loading: PizzaDetailsUiState

    data class Error(val message: String) : PizzaDetailsUiState

    data class Content(val pizzaDetails: PizzaDetailsUiModel) : PizzaDetailsUiState
}