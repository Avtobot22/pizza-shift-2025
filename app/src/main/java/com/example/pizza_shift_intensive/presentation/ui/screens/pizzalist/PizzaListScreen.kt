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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_shift_intensive.presentation.model.PizzaUiModel
import com.example.pizza_shift_intensive.presentation.ui.components.ErrorMessage
import com.example.pizza_shift_intensive.presentation.ui.components.FullScreenProgressIndicator
import com.example.pizza_shift_intensive.presentation.ui.components.PizzaImage
import com.example.pizza_shift_intensive.presentation.ui.components.Title
import com.example.pizza_shift_intensive.presentation.viewmodel.PizzaListViewModel


@Composable
fun PizzaListScreen(
    viewModel: PizzaListViewModel = viewModel(),
    onPizzaClick: (pizzaId: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            FullScreenProgressIndicator()
        }
        uiState.errorMessage != null -> {
            ErrorMessage(
                message = uiState.errorMessage ?: "Неизвестная ошибка",
                onRetry = { viewModel.loadPizzas() }
            )
        }
        else -> {
            PizzaListContent(pizzaUiModels = uiState.pizzaUiModelList, onPizzaClick = onPizzaClick)
        }
    }
}

@Composable
fun PizzaListContent(pizzaUiModels: List<PizzaUiModel>, onPizzaClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(5.dp)) {
        Title()

        PizzaList(pizzaUiModels, onPizzaClick)
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

        Column(modifier = Modifier.weight(1f)) {
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
}