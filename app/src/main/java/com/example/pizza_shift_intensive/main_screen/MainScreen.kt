package com.example.pizza_shift_intensive.main_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pizza_shift_intensive.pizza_list_screen.PizzaScreenContent


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Scaffold { paddingValues ->
        PizzaScreenContent(modifier = Modifier.padding(top = paddingValues.calculateTopPadding() / 2))
    }
}
