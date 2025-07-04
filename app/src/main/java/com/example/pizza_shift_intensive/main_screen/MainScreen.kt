package com.example.pizza_shift_intensive.main_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pizza_shift_intensive.pizza_details_screen.DetailsRoute
import com.example.pizza_shift_intensive.pizza_details_screen.PizzaDetailsScreen
import com.example.pizza_shift_intensive.pizza_list_screen.PizzaListRoute
import com.example.pizza_shift_intensive.pizza_list_screen.PizzaScreenContent


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
                PizzaScreenContent(
                    onItemClick = { pizzaId ->
                        navigationController.navigate(DetailsRoute(pizzaId))
                    }
                )
            }
            composable<DetailsRoute> {
                val destination = it.toRoute<DetailsRoute>()
                PizzaDetailsScreen(
                    id = destination.pizzaId
                )
            }
        }
    }
}