package com.example.pizza_shift_intensive

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails.DetailsRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails.PizzaDetailsScreen
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist.PizzaListRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist.PizzaListScreen


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navigationController = rememberNavController()

    Scaffold(modifier = modifier) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = PizzaListRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<PizzaListRoute> {
                PizzaListScreen(
                    onPizzaClick = { pizzaId ->
                        navigationController.navigate(DetailsRoute(pizzaId = pizzaId))
                    }
                )
            }
            composable<DetailsRoute> {
                PizzaDetailsScreen()
            }
        }
    }
}