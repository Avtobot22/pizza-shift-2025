package com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pizza_shift_intensive.R
import com.example.pizza_shift_intensive.presentation.model.ComponentUiModel
import com.example.pizza_shift_intensive.presentation.model.PizzaDetailsUiModel
import com.example.pizza_shift_intensive.presentation.model.SizesUiModel
import com.example.pizza_shift_intensive.presentation.ui.components.ErrorMessage
import com.example.pizza_shift_intensive.presentation.ui.components.FullScreenProgressIndicator
import com.example.pizza_shift_intensive.presentation.ui.components.ItemImage
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
                onToppingSelected = { selectedTopping ->
                    pizzaDetailsViewModel.setSelectedTopping(selectedTopping = selectedTopping)
                },
                isToppingsExpanded = currentState.isToppingsExpanded,
                onToppingExpandedChanged = { isExpanded ->
                    pizzaDetailsViewModel.setToppingsExpanded(isExpanded = isExpanded)
                },
                onAddToCart = { TODO() }
            )
        }
    }

}

@Composable
private fun PizzaDetailsContent(
    pizza: PizzaDetailsUiModel,
    onSizeSelected: (SizesUiModel) -> Unit,
    onToppingSelected: (ComponentUiModel) -> Unit,
    isToppingsExpanded: Boolean,
    onToppingExpandedChanged: (Boolean) -> Unit,
    onAddToCart: (PizzaDetailsUiModel) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ItemImage(pizza.img, size = 120.dp)
                PizzaName(pizza)
                PizzaInfo(pizza)
                PizzaComposition(pizza)
                PizzaSize(pizza, onSizeSelected)
                Text(
                    stringResource(R.string.toppings_title),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 8.dp)
                )
            }

            if (isToppingsExpanded) {
                items(pizza.toppings.chunked(3)) { rowItems ->
                    ToppingsRow(rowItems, pizza, onToppingSelected)
                }
                item {
                    TextButton(
                        onClick = { onToppingExpandedChanged(false) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.hide_all_toppings))
                    }
                }
            } else {
                pizza.toppings.take(3).chunked(3).forEach { rowItems ->
                    item {
                        ToppingsRow(rowItems, pizza, onToppingSelected)
                    }
                }
                if (pizza.toppings.size > 3) {
                    item {
                        TextButton(
                            onClick = { onToppingExpandedChanged(true) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(stringResource(R.string.show_all_toppings))
                        }
                    }
                }
            }
        }

        Cart(
            pizza = pizza,
            onAddToCart = { onAddToCart(pizza) },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun ToppingsRow(
    rowItems: List<ComponentUiModel>,
    pizza: PizzaDetailsUiModel,
    onToppingSelected: (ComponentUiModel) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        rowItems.forEach { topping ->
            Box(modifier = Modifier.weight(1f)) {
                val isSelected = pizza.selectedToppings.contains(topping)
                Topping(
                    pizzaTopping = topping,
                    onToppingSelected = onToppingSelected,
                    isSelected = isSelected
                )
            }
        }
        repeat(3 - rowItems.size) {
            Box(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PizzaSize(
    pizza: PizzaDetailsUiModel,
    onSizeSelected: (SizesUiModel) -> Unit
) {
    Text(
        text = stringResource(R.string.size_title),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(bottom = 5.dp)
    )

    PizzaSizeSelector(pizza, onSizeSelected = onSizeSelected)
}

@Composable
private fun PizzaInfo(pizza: PizzaDetailsUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                R.string.pizza_details_info,
                pizza.selectedSize.diameter,
                pizza.selectedDough.type
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun PizzaComposition(pizza: PizzaDetailsUiModel) {
    Text(
        text = stringResource(R.string.pizza_details_ingredients, pizza.ingredients),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
private fun PizzaName(pizza: PizzaDetailsUiModel) {
    Text(
        text = pizza.name,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(bottom = 5.dp),
        fontWeight = FontWeight.ExtraBold
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
            .padding(5.dp),
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
private fun Topping(
    pizzaTopping: ComponentUiModel,
    onToppingSelected: (ComponentUiModel) -> Unit,
    isSelected: Boolean
) {
    val borderWidth by animateDpAsState(targetValue = if (isSelected) 4.dp else 0.dp)
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clickable { onToppingSelected(pizzaTopping) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(borderWidth, borderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ItemImage(pizzaTopping.img, size = 70.dp)

            Text(
                text = pizzaTopping.type,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = stringResource(R.string.price, pizzaTopping.price),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun Cart(
    pizza: PizzaDetailsUiModel,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onAddToCart,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = stringResource(R.string.cart, pizza.price),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}