package com.example.pizza_shift_intensive.pizza_list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.pizza_shift_intensive.pizza_list_screen.data.PizzaDataSource
import com.example.pizza_shift_intensive.pizza_list_screen.data.PizzaListItem

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        text = "Пицца",
        modifier = modifier.padding(start = 16.dp),
        style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
        )
    )
}

@Composable
fun PizzaScreenContent(modifier: Modifier = Modifier, onItemClick: (pizzaId: Long) -> Unit) {
    Column(modifier = modifier.padding(5.dp)) {
        Title(modifier = modifier)
        PizzaList(modifier = modifier.fillMaxWidth(), onItemClick)
    }
}

@Composable
private fun PizzaList(modifier: Modifier = Modifier, onItemClick: (pizzaId: Long) -> Unit) {
    val pizzas = PizzaDataSource.pizzaItems
    LazyColumn(modifier = Modifier) {
        items(pizzas) { pizza ->
            PizzaListItem(
                item = pizza,
                onClick = onItemClick
            )
        }
    }
}

@Composable
private fun PizzaListItem(item: PizzaListItem , onClick: (pizzaId: Long) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item.id) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.name,
            modifier = Modifier
                .size(96.dp)
                .padding(end = 16.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "от ${item.price} ₽",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}