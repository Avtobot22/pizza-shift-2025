package com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pizza_shift_intensive.presentation.model.PizzaDetailsUiModel
import com.example.pizza_shift_intensive.presentation.model.SizesUiModel
import com.example.pizza_shift_intensive.presentation.ui.components.ErrorMessage
import com.example.pizza_shift_intensive.presentation.ui.components.FullScreenProgressIndicator
import com.example.pizza_shift_intensive.presentation.ui.components.PizzaImage
import com.example.pizza_shift_intensive.presentation.ui.components.Title
import com.example.pizza_shift_intensive.presentation.viewmodel.PizzaDetailsUiState
import com.example.pizza_shift_intensive.presentation.viewmodel.PizzaViewModel


@Composable
fun PizzaDetailsScreen(
    pizzaViewModel: PizzaViewModel,
    pizzaId: String
) {
    val pizzaDetailsUiState by pizzaViewModel.pizzaDetailsUiState.observeAsState(PizzaDetailsUiState.Loading)

    when (val currentState = pizzaDetailsUiState) {
        is PizzaDetailsUiState.Loading -> FullScreenProgressIndicator()
        is PizzaDetailsUiState.Error -> ErrorMessage(
            message = currentState.message,
            onRetry = { pizzaViewModel.getPizzaDetails(pizzaId) }
        )

        is PizzaDetailsUiState.Content -> {
            PizzaDetailsContent(
                pizza = currentState.pizzaDetails,
                onSizeSelected = { TODO("Изменение размера пиццы") }
            )
        }
    }

}

@Composable
private fun PizzaDetailsContent(
    pizza: PizzaDetailsUiModel,
    onSizeSelected: (SizesUiModel) -> Unit
) {
    Title(modifier = Modifier.padding(bottom = 16.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PizzaImage(pizza.img, size = 160.dp)

        Text(
            text = pizza.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${pizza.selectedSize.diameter} см, ${pizza.selectedDough.type}",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "${pizza.price} ₽",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Состав: ${pizza.ingredients}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Выберите размер:",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        PizzaSizeSelector(pizza, onSizeSelected = onSizeSelected)

    }
}

@Composable
private fun PizzaSizeSelector(
    pizza: PizzaDetailsUiModel,
    onSizeSelected: (SizesUiModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        pizza.sizes.forEach { size ->
            val isSelected = pizza.selectedSize == size
            TextButton(
                onClick = { onSizeSelected(size) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(text = size.type)
            }
        }
    }

}