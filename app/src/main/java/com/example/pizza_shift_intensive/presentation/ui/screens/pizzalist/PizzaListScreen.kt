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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import com.example.pizza_shift_intensive.presentation.model.PizzaUiModel
import com.example.pizza_shift_intensive.presentation.ui.components.ErrorMessage
import com.example.pizza_shift_intensive.presentation.ui.components.FullScreenProgressIndicator
import com.example.pizza_shift_intensive.presentation.ui.components.PizzaImage
import com.example.pizza_shift_intensive.presentation.viewmodel.PizzaListUiState
import com.example.pizza_shift_intensive.presentation.viewmodel.PizzaViewModel


@Composable
fun PizzaListScreen(
    pizzaViewModel: PizzaViewModel,
    onPizzaClick: (pizzaId: String) -> Unit
) {
    val pizzaListUiState by pizzaViewModel.pizzaListUiState.observeAsState(PizzaListUiState.Loading)

    when (val currentState = pizzaListUiState) {
        is PizzaListUiState.Loading -> FullScreenProgressIndicator()
        is PizzaListUiState.Error -> ErrorMessage(message = currentState.message,
            onRetry = { pizzaViewModel.getPizzas() }
        )
        is PizzaListUiState.Content -> {
            PizzaList(currentState.pizzas, onPizzaClick, pizzaViewModel)
        }
    }
}


@Composable
private fun PizzaList(pizzaUiModels: List<PizzaUiModel>, onPizzaClick: (String) -> Unit, pizzaViewModel: PizzaViewModel) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(pizzaUiModels) { pizza ->
            PizzaListItem(pizzaUiModel = pizza, onClick = onPizzaClick, pizzaViewModel)
        }
    }
}

@Composable
private fun PizzaListItem(pizzaUiModel: PizzaUiModel, onClick: (String) -> Unit, pizzaViewModel: PizzaViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(pizzaUiModel.id)
                pizzaViewModel.getPizzaDetails(pizzaUiModel.id)
            }
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