package com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_intensive.domain.usecase.GetPizzaDetailsUseCase
import com.example.pizza_shift_intensive.domain.usecase.PizzaDetailsResult
import com.example.pizza_shift_intensive.presentation.mappers.toDetailsUiModel
import com.example.pizza_shift_intensive.presentation.model.ComponentUiModel
import com.example.pizza_shift_intensive.presentation.model.SizesUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//class PizzaDetailsViewModel(
//    private val pizzaId: String,
//    private val getPizzaDetailsUseCase: GetPizzaDetailsUseCase
//) : ViewModel() {
//    private val _uiState = MutableStateFlow(PizzaDetailsUiState())
//    val uiState: StateFlow<PizzaDetailsUiState> = _uiState
//
//    init {
//        loadPizzaDetails()
//    }
//
//    fun loadPizzaDetails() {
//        viewModelScope.launch {
//            _uiState.update { it.copy(isLoading = true) }
//
//            when (val result = getPizzaDetailsUseCase(pizzaId)) {
//                is PizzaDetailsResult.Success -> {
//                    val pizzaUiModel = result.pizza.toDetailsUiModel(result.price)
//                    _uiState.update {
//                        it.copy(
//                            isLoading = false,
//                            pizzaDetailsItem = pizzaUiModel,
//                            errorMessage = null
//                        )
//                    }
//                }
//
//                is PizzaDetailsResult.NotFound -> {
//                    _uiState.update {
//                        it.copy(
//                            isLoading = false,
//                            errorMessage = "Пицца не найдена"
//                        )
//                    }
//                }
//            }
//
//        }
//    }
//
//    fun updateSelectedSize(size: SizesUiModel) {
//        _uiState.update { currentState ->
//            val oldPizza = currentState.pizzaDetailsItem ?: return@update currentState
//            val newPrice = calculateNewPrice(
//                size = size,
//                dough = oldPizza.selectedDough,
//                toppings = oldPizza.toppings
//            )
//            currentState.copy(
//                pizzaDetailsItem = oldPizza.copy(
//                    selectedSize = size,
//                    price = newPrice
//                )
//            )
//        }
//    }
//
//    private fun calculateNewPrice(
//        size: SizesUiModel,
//        dough: ComponentUiModel,
//        toppings: List<ComponentUiModel>
//    ): Int {
//        return size.price + dough.price + toppings.sumOf { it.price }
//    }
//
//}