package com.example.pizza_shift_intensive.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_intensive.domain.usecase.GetPizzasWithInitialPriceUseCase
import com.example.pizza_shift_intensive.presentation.mappers.toUiModel
import com.example.pizza_shift_intensive.presentation.model.PizzaUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PizzaListUIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val pizzaUiModelList: List<PizzaUiModel> = emptyList()
)

class PizzaListViewModel(
    private val getPizzasWithInitialPriceUseCase: GetPizzasWithInitialPriceUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PizzaListUIState())
    val uiState: StateFlow<PizzaListUIState> = _uiState

    init {
        loadPizzas()
    }

    fun loadPizzas() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val pizzasWithPrices = getPizzasWithInitialPriceUseCase()
                val pizzaUiModels = pizzasWithPrices.map { (pizzaModel, price) ->
                    pizzaModel.toUiModel(price)
                }
                _uiState.update { it.copy(isLoading = false, pizzaUiModelList = pizzaUiModels) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }
}