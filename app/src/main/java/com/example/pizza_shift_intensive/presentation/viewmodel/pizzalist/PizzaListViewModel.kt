package com.example.pizza_shift_intensive.presentation.viewmodel.pizzalist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_intensive.domain.usecase.GetPizzasWithInitialPriceScenario
import com.example.pizza_shift_intensive.presentation.mappers.toUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PizzaListViewModel(
    private val getPizzasWithInitialPriceScenario: GetPizzasWithInitialPriceScenario,
) : ViewModel() {

    private val _pizzaListUiState = MutableLiveData<PizzaListUiState>(
        PizzaListUiState.Loading
    )
    val pizzaListUiState: LiveData<PizzaListUiState> = _pizzaListUiState


    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("PizzaListViewModel", exception.message.toString())
        _pizzaListUiState.value = PizzaListUiState.Error(exception.message ?: "Неизвестная ошибка")
    }
    fun getPizzas() {
        _pizzaListUiState.value = PizzaListUiState.Loading
        viewModelScope.launch(handler) {
            val pizzasWithPrice = getPizzasWithInitialPriceScenario()
            val pizzasUi = pizzasWithPrice.map { it.first.toUiModel(it.second) }

            _pizzaListUiState.value = PizzaListUiState.Content(pizzasUi)

        }
    }

}