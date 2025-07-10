package com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_intensive.domain.usecase.GetPizzaDetailsUseCase
import com.example.pizza_shift_intensive.domain.usecase.PizzaDetailsResult
import com.example.pizza_shift_intensive.presentation.mappers.toDetailsUiModel
import com.example.pizza_shift_intensive.presentation.model.ComponentUiModel
import com.example.pizza_shift_intensive.presentation.model.SizesUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class PizzaDetailsViewModel(
    private val getPizzaDetailsUseCase: GetPizzaDetailsUseCase
) : ViewModel() {
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
            when (pizzaDetails) {
                is PizzaDetailsResult.Success ->
                    _pizzaDetailsUiState.value =
                        PizzaDetailsUiState.Content(
                            pizzaDetails.pizza.toDetailsUiModel(pizzaDetails.price),
                            isToppingsExpanded = false
                        )

                is PizzaDetailsResult.NotFound ->
                    _pizzaDetailsUiState.value = PizzaDetailsUiState.Error("Пицца не найдена")

            }
        }
    }

    fun setSelectedSize(selectedSize: SizesUiModel) {
        val currentState = _pizzaDetailsUiState.value
        if (currentState is PizzaDetailsUiState.Content) {
            val totalPrice = calculatePrice(
                selectedSize,
                currentState.pizzaDetails.selectedToppings,
            )
            val updatedPizzaDetails =
                currentState.pizzaDetails.copy(selectedSize = selectedSize, price = totalPrice)
            _pizzaDetailsUiState.value = PizzaDetailsUiState.Content(
                updatedPizzaDetails,
                isToppingsExpanded = currentState.isToppingsExpanded
            )
        }

    }

    fun setSelectedTopping(selectedTopping: ComponentUiModel) {
        val currentState = _pizzaDetailsUiState.value
        if (currentState is PizzaDetailsUiState.Content) {
            val selectedToppings =
                currentState.pizzaDetails.selectedToppings.toMutableList()
            if (selectedToppings.contains(selectedTopping)) {
                selectedToppings.remove(selectedTopping)
            } else {
                selectedToppings.add(selectedTopping)
            }
            val totalPrice = calculatePrice(
                currentState.pizzaDetails.selectedSize,
                selectedToppings,
            )
            val updatedPizzaDetails = currentState.pizzaDetails.copy(
                selectedToppings = selectedToppings,
                price = totalPrice
            )
            _pizzaDetailsUiState.value = PizzaDetailsUiState.Content(
                updatedPizzaDetails,
                isToppingsExpanded = currentState.isToppingsExpanded
            )
        }
    }

    fun setToppingsExpanded(isExpanded: Boolean) {
        val currentState = _pizzaDetailsUiState.value
        if (currentState is PizzaDetailsUiState.Content) {
            _pizzaDetailsUiState.value = currentState.copy(isToppingsExpanded = isExpanded)
        }
    }

    private fun calculatePrice(
        size: SizesUiModel,
        toppings: List<ComponentUiModel>,
    ): Int {
        return size.price + toppings.sumOf { it.price }
    }
}