package com.example.pizza_shift_intensive.presentation.ui.screens.mainscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails.DetailsRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails.PizzaDetailsScreen
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist.PizzaListRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist.PizzaListScreen
import com.example.pizza_shift_intensive.presentation.viewmodel.PizzaViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MyApp(modifier: Modifier = Modifier, pizzaViewModel: PizzaViewModel = koinViewModel()) {
    val navigationController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        pizzaViewModel.getPizzas()
    }

    Scaffold(modifier = modifier) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = PizzaListRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<PizzaListRoute> {
                PizzaListScreen(
                    pizzaViewModel = pizzaViewModel,
                    onPizzaClick = { pizzaId ->
                        navigationController.navigate(DetailsRoute(pizzaId = pizzaId))
                    }
                )
            }
            composable<DetailsRoute> {
                val destination = it.toRoute<DetailsRoute>()
                PizzaDetailsScreen(pizzaViewModel, destination.pizzaId)
            }
        }
    }
}