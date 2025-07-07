package com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.pizza_shift_intensive.data.api.NetworkModule
import com.example.pizza_shift_intensive.data.repository.PizzaRepository
import com.example.pizza_shift_intensive.data.repository.PizzaRepositoryImpl
import com.example.pizza_shift_intensive.domain.usecase.GetInitialPizzaPriceUseCase
import com.example.pizza_shift_intensive.domain.usecase.GetPizzasUseCase
import com.example.pizza_shift_intensive.domain.usecase.GetPizzasWithInitialPriceUseCase
import com.example.pizza_shift_intensive.presentation.mappers.toUiModel
import com.example.pizza_shift_intensive.presentation.model.PizzaUiModel
import com.example.pizza_shift_intensive.presentation.ui.components.ErrorMessage
import com.example.pizza_shift_intensive.presentation.ui.components.FullScreenProgressIndicator
import com.example.pizza_shift_intensive.presentation.ui.components.PizzaImage
import com.example.pizza_shift_intensive.presentation.ui.components.Title
import com.example.pizza_shift_intensive.presentation.viewmodel.pizzalist.PizzaListUiState
import kotlinx.coroutines.launch


@Composable
fun PizzaListScreen(
    onPizzaClick: (pizzaId: String) -> Unit
) {
    var state: PizzaListUiState by remember { mutableStateOf(PizzaListUiState.Loading) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        state = PizzaListUiState.Loading
        try {
            val pizzas = getPizzasList()
            state = PizzaListUiState.Content(pizzas = pizzas)
        } catch (e: Exception) {
            state = PizzaListUiState.Error(message = e.message.orEmpty())
        }
    }

    Column(modifier = Modifier.padding(5.dp)) {
        Title()

        when (val currentState = state) {
            PizzaListUiState.Loading -> FullScreenProgressIndicator()

            is PizzaListUiState.Content -> PizzaList(
                pizzaUiModels = currentState.pizzas,
                onPizzaClick = onPizzaClick
            )

            is PizzaListUiState.Error -> ErrorMessage(
                message = currentState.message,
                onRetry = {
                    coroutineScope.launch {
                        state = PizzaListUiState.Loading
                        try {
                            val pizzas = getPizzasList()
                            state = PizzaListUiState.Content(pizzas = pizzas)
                        } catch (e: Exception) {
                            state = PizzaListUiState.Error(message = e.message.orEmpty())
                        }
                    }
                }
            )
        }
    }
}


@Composable
private fun PizzaList(pizzaUiModels: List<PizzaUiModel>, onPizzaClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(pizzaUiModels) { pizza ->
            PizzaListItem(pizzaUiModel = pizza, onClick = onPizzaClick)
        }
    }
}

@Composable
private fun PizzaListItem(pizzaUiModel: PizzaUiModel, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(pizzaUiModel.id) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PizzaImage(pizzaUiModel.img)

        PizzaDetails(pizzaUiModel = pizzaUiModel, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun PizzaDetails(pizzaUiModel: PizzaUiModel, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = pizzaUiModel.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = pizzaUiModel.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Text(
            text = "от ${pizzaUiModel.price} ₽",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

private suspend fun getPizzasList(): List<PizzaUiModel> {
    val pizzaRepository: PizzaRepository = PizzaRepositoryImpl(
        NetworkModule.pizzaApi,
        NetworkModule.pizzaConverter
    )

    val pizzasWithPrices = GetPizzasWithInitialPriceUseCase(
        getPizzasUseCase = GetPizzasUseCase(
            pizzaRepository
        ), getInitialPizzaPriceUseCase = GetInitialPizzaPriceUseCase()
    )()

    return pizzasWithPrices.map { (pizzaModel, price) ->
        pizzaModel.toUiModel(price)
    }
}