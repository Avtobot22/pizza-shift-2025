package com.example.pizza_shift_intensive.presentation.viewmodel

import com.example.pizza_shift_intensive.presentation.model.PizzaDetailsUiModel

sealed interface PizzaDetailsUiState{
    data object isLoading: PizzaDetailsUiState

    data class Error(val message: String) : PizzaDetailsUiState

    data class Content(val pizzaDetails: PizzaDetailsUiModel) : PizzaDetailsUiState
}