package com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.pizza_shift_intensive.presentation.model.ComponentUiModel
import com.example.pizza_shift_intensive.presentation.model.PizzaDetailsUiModel
import com.example.pizza_shift_intensive.presentation.model.SizesUiModel
import com.example.pizza_shift_intensive.presentation.ui.components.ErrorMessage
import com.example.pizza_shift_intensive.presentation.ui.components.FullScreenProgressIndicator
import com.example.pizza_shift_intensive.presentation.ui.components.ItemImage
import com.example.pizza_shift_intensive.presentation.ui.components.Title
import com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails.PizzaDetailsUiState
import com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails.PizzaDetailsViewModel


@Composable
fun PizzaDetailsScreen(
    pizzaDetailsViewModel: PizzaDetailsViewModel,
    pizzaId: String
) {
    val pizzaDetailsUiState by pizzaDetailsViewModel.pizzaDetailsUiState.observeAsState(
        PizzaDetailsUiState.Loading
    )

    when (val currentState = pizzaDetailsUiState) {
        is PizzaDetailsUiState.Loading -> FullScreenProgressIndicator()
        is PizzaDetailsUiState.Error -> ErrorMessage(
            message = currentState.message,
            onRetry = { pizzaDetailsViewModel.getPizzaDetails(pizzaId) }
        )

        is PizzaDetailsUiState.Content -> {
            PizzaDetailsContent(
                pizza = currentState.pizzaDetails,
                onSizeSelected = { selectedSize ->
                    pizzaDetailsViewModel.setSelectedSize(
                        selectedSize
                    )
                },
                onToppingSelected = { TODO("Добавление топпинга в заказ")}
            )
        }
    }

}

@Composable
private fun PizzaDetailsContent(
    pizza: PizzaDetailsUiModel,
    onSizeSelected: (SizesUiModel) -> Unit,
    onToppingSelected: (ComponentUiModel) -> Unit
) {
    Title(modifier = Modifier.padding(bottom = 16.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ItemImage(pizza.img, size = 160.dp)

        PizzaName(pizza)

        PizzaInfo(pizza)

        PizzaComposition(pizza)

        PizzaSize(pizza, onSizeSelected)

        PizzaToppings(
            pizzaToppings = pizza.toppings,
            onToppingSelected = onToppingSelected,
            )

    }
}

@Composable
private fun ColumnScope.PizzaSize(
    pizza: PizzaDetailsUiModel,
    onSizeSelected: (SizesUiModel) -> Unit
) {
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

@Composable
private fun PizzaInfo(pizza: PizzaDetailsUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${pizza.selectedSize.diameter} см, ${pizza.selectedDough.type}",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "${pizza.price} ₽",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PizzaComposition(pizza: PizzaDetailsUiModel) {
    Text(
        text = "Состав: ${pizza.ingredients}",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}

@Composable
private fun PizzaName(pizza: PizzaDetailsUiModel) {
    Text(
        text = pizza.name,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(bottom = 16.dp)
    )
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

@Composable
private fun PizzaToppings(
    pizzaToppings: List<ComponentUiModel>,
    onToppingSelected: (ComponentUiModel) -> Unit
) {
    Text(
        "Добавить по вкусу",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 8.dp)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pizzaToppings) { topping ->
            Topping(pizzaTopping = topping, onToppingSelected = onToppingSelected)
        }
    }
}

@Composable
private fun Topping(pizzaTopping: ComponentUiModel, onToppingSelected: (ComponentUiModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onToppingSelected(pizzaTopping) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = pizzaTopping.type,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            ItemImage(pizzaTopping.img, size = 70.dp)
            Text(
                text = "${pizzaTopping.price} ₽",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}