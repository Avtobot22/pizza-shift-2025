package com.example.pizza_shift_intensive.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_intensive.domain.usecase.GetPizzaDetailsUseCase
import com.example.pizza_shift_intensive.domain.usecase.GetPizzasWithInitialPriceUseCase
import com.example.pizza_shift_intensive.domain.usecase.PizzaDetailsResult
import com.example.pizza_shift_intensive.presentation.mappers.toDetailsUiModel
import com.example.pizza_shift_intensive.presentation.mappers.toUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PizzaViewModel(
    private val getPizzasWithInitialPriceUseCase: GetPizzasWithInitialPriceUseCase,
    private val getPizzaDetailsUseCase: GetPizzaDetailsUseCase,
) : ViewModel() {

    private val _pizzaListUiState = MutableLiveData<PizzaListUiState>(
        PizzaListUiState.Loading
    )
    val pizzaListUiState: LiveData<PizzaListUiState> = _pizzaListUiState

    private val _pizzaDetailsUiState = MutableLiveData<PizzaDetailsUiState>(
        PizzaDetailsUiState.isLoading
    )
    val pizzaDetailsUiState: LiveData<PizzaDetailsUiState> = _pizzaDetailsUiState

    fun getPizzas() {
        _pizzaListUiState.value = PizzaListUiState.Loading
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("PizzaViewModel", exception.message.toString())
            _pizzaListUiState.value = PizzaListUiState.Error(exception.message ?: "Неизвестная ошибка")
        }
        viewModelScope.launch(handler) {
            val pizzasWithPrice = getPizzasWithInitialPriceUseCase()
            val pizzasUi = pizzasWithPrice.map { it.first.toUiModel(it.second) }

            _pizzaListUiState.value = PizzaListUiState.Content(pizzasUi)

        }
    }

    fun getPizzaDetails(pizzaId: String) {
        _pizzaDetailsUiState.value = PizzaDetailsUiState.isLoading
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("PizzaViewModel", exception.message.toString())
            _pizzaDetailsUiState.value = PizzaDetailsUiState.Error(exception.message ?: "Неизвестная ошибка")
        }
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