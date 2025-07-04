package com.example.pizza_shift_intensive.pizza_details_screen

import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.pizza_shift_intensive.pizza_details_screen.data.PizzaDetailsItem
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pizza_shift_intensive.pizza_details_screen.data.PizzasPrices
import com.example.pizza_shift_intensive.pizza_details_screen.data.getPizzaDetailsById
import com.example.pizza_shift_intensive.pizza_list_screen.Title


@Composable
fun PizzaDetailsScreen(modifier: Modifier = Modifier, id: Long) {
    val pizza: PizzaDetailsItem? = getPizzaDetailsById(id)

    Title(modifier = Modifier.padding(bottom = 16.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (pizza == null) {
            Text(
                text = "Пицца не найдена",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            return
        }

        var currentSize by rememberSaveable { mutableStateOf(pizza.selectedSize) }
        val currentPizza = pizza.copy(selectedSize = currentSize)

        Image(
            painter = painterResource(id = currentPizza.imageResId),
            contentDescription = currentPizza.name,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(160.dp)
        )

        Text(
            text = currentPizza.name,
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
                text = "${currentPizza.selectedSize.diameter} см, традиционное тесто",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "${currentPizza.finalPrice} ₽",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Состав: ${currentPizza.ingredients.joinToString(", ")}",
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

        PizzaSizeSelector(
            pizza = currentPizza,
            onSizeSelected = { size ->
                currentSize = size
            },
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
private fun PizzaSizeSelector(
    pizza: PizzaDetailsItem,
    onSizeSelected: (PizzasPrices) -> Unit,
    modifier: Modifier = Modifier
) {
    val sizes = PizzasPrices.entries.toTypedArray()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        sizes.forEach { size ->
            val isSelected = pizza.selectedSize == size
            val selectedColor = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surface
            }
            val selectedContentColor = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
            TextButton(
                onClick = { onSizeSelected(size) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = selectedColor,
                    contentColor = selectedContentColor
                )
            ) {
                Text(text = size.displayName)
            }
        }
    }
}