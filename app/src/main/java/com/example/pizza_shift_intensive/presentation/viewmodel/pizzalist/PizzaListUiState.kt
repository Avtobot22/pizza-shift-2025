package com.example.pizza_shift_intensive.presentation.viewmodel.pizzalist

import com.example.pizza_shift_intensive.presentation.model.PizzaUiModel

sealed interface PizzaListUiState{
    data object Loading : PizzaListUiState

    data class Error(val message: String) : PizzaListUiState

    data class Content(val pizzas: List<PizzaUiModel>) : PizzaListUiState
}