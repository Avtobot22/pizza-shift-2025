package com.example.pizza_shift_intensive.presentation.viewmodel.pizzalist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_intensive.domain.usecase.GetPizzasWithInitialPriceUseCase
import com.example.pizza_shift_intensive.presentation.mappers.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//class PizzaListViewModel(
//    private val getPizzasWithInitialPriceUseCase: GetPizzasWithInitialPriceUseCase
//) : ViewModel() {
//    private val _uiState = MutableStateFlow(PizzaListUiState())
//    val uiState: StateFlow<PizzaListUiState> = _uiState
//
//    init {
//        loadPizzas()
//    }
//
//    fun loadPizzas() {
//        viewModelScope.launch {
//            _uiState.update { it.copy(isLoading = true) }
//            try {
//                val pizzasWithPrices = getPizzasWithInitialPriceUseCase()
//                val pizzaUiModels = pizzasWithPrices.map { (pizzaModel, price) ->
//                    pizzaModel.toUiModel(price)
//                }
//                _uiState.update { it.copy(isLoading = false, pizzaUiModelList = pizzaUiModels) }
//            } catch (e: Exception) {
//                _uiState.update {
//                    it.copy(
//                        isLoading = false,
//                        errorMessage = e.message
//                    )
//                }
//            }
//        }
//    }
//}