package com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_intensive.domain.usecase.GetPizzaDetailsUseCase
import com.example.pizza_shift_intensive.domain.usecase.PizzaDetailsResult
import com.example.pizza_shift_intensive.presentation.mappers.toDetailsUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch



class PizzaDetailsViewModel(
    private val getPizzaDetailsUseCase: GetPizzaDetailsUseCase
): ViewModel() {
    private val _pizzaDetailsUiState = MutableLiveData<PizzaDetailsUiState>(
        PizzaDetailsUiState.Loading
    )
    val pizzaDetailsUiState: LiveData<PizzaDetailsUiState> = _pizzaDetailsUiState

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("PizzaDetailsViewModel", exception.message.toString())
        _pizzaDetailsUiState.value =
            PizzaDetailsUiState.Error(exception.message ?: "Неизвестная ошибка")
    }
    fun getPizzaDetails(pizzaId: String) {
        _pizzaDetailsUiState.value = PizzaDetailsUiState.Loading
        viewModelScope.launch(handler) {
            val pizzaDetails = getPizzaDetailsUseCase(pizzaId)
            when(pizzaDetails) {
                is PizzaDetailsResult.Success -> {
                    _pizzaDetailsUiState.value = PizzaDetailsUiState.Content(pizzaDetails.pizza.toDetailsUiModel(pizzaDetails.price))
                }
                is PizzaDetailsResult.NotFound -> {
                    _pizzaDetailsUiState.value = PizzaDetailsUiState.Error("Пицца не найдена")
                }
            }
        }
    }
}