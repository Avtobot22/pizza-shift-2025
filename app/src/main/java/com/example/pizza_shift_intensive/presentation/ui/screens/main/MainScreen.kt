package com.example.pizza_shift_intensive.presentation.ui.screens.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pizza_shift_intensive.R
import com.example.pizza_shift_intensive.presentation.ui.components.Title
import com.example.pizza_shift_intensive.presentation.ui.navigation.NavigationOption
import com.example.pizza_shift_intensive.presentation.ui.screens.cart.CartRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.cart.CartScreen
import com.example.pizza_shift_intensive.presentation.ui.screens.orders.OrdersRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.orders.OrdersScreen
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails.DetailsRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzzadetails.PizzaDetailsScreen
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist.PizzaListRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist.PizzaListScreen
import com.example.pizza_shift_intensive.presentation.ui.screens.profile.ProfileRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.profile.ProfileScreen
import com.example.pizza_shift_intensive.presentation.viewmodel.pizzadetails.PizzaDetailsViewModel
import com.example.pizza_shift_intensive.presentation.viewmodel.pizzalist.PizzaListViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MyApp(modifier: Modifier = Modifier, pizzaListViewModel: PizzaListViewModel = koinViewModel()) {
    val navigationController = rememberNavController()
    val selectedTab = rememberSaveable { mutableStateOf(NavigationOption.PIZZAS) }

    LaunchedEffect(key1 = Unit) {
        pizzaListViewModel.getPizzas()
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            val openedOption =
                NavigationOption.entries.firstOrNull { destination.hasRoute(it.route) }
            if (openedOption != null) {
                selectedTab.value = openedOption
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = { Title() },
        bottomBar = {
            BottomNavigation(
                navigationOptions = NavigationOption.entries,
                selectedNavigationOption = selectedTab.value,
                onItemClicked = { option ->
                    when (option) {
                        NavigationOption.PIZZAS -> navigationController.openPoppingAllPrevious(
                            PizzaListRoute
                        )

                        NavigationOption.ORDERS -> navigationController.openPoppingAllPrevious(
                            OrdersRoute
                        )

                        NavigationOption.CART -> navigationController.openPoppingAllPrevious(
                            CartRoute
                        )

                        NavigationOption.PROFILE -> navigationController.openPoppingAllPrevious(
                            ProfileRoute
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = PizzaListRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            animatedComposable<PizzaListRoute> {
                PizzaListScreen(
                    pizzaListViewModel = pizzaListViewModel,
                    onPizzaClick = { pizzaId ->
                        navigationController.navigate(DetailsRoute(pizzaId = pizzaId))
                    }
                )
            }
            animatedComposable<DetailsRoute> {
                val destination = it.toRoute<DetailsRoute>()
                val pizzaDetailsViewModel = koinViewModel<PizzaDetailsViewModel>()
                LaunchedEffect(key1 = Unit) {
                    pizzaDetailsViewModel.getPizzaDetails(destination.pizzaId)
                }
                PizzaDetailsScreen(pizzaDetailsViewModel, destination.pizzaId)
            }
            animatedComposable<OrdersRoute> {
                OrdersScreen()
            }
            animatedComposable<CartRoute> {
                CartScreen()
            }
            animatedComposable<ProfileRoute> {
                ProfileScreen()
            }
        }
    }
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(noinline block: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit) {
    composable<T>(
        enterTransition = ENTER_TRANSITION,
        exitTransition = EXIT_TRANSITION,
        popEnterTransition = POP_ENTER_TRANSITION,
        popExitTransition = POP_EXIT_TRANSITION,
        content = block
    )
}


@Composable
private fun BottomNavigation(
    navigationOptions: List<NavigationOption>,
    selectedNavigationOption: NavigationOption,
    onItemClicked: (NavigationOption) -> Unit,
) {
    NavigationBar {
        for (option in navigationOptions) {
            NavigationBarItem(
                selected = selectedNavigationOption == option,
                onClick = { onItemClicked(option) },
                icon = { Icon(getIcon(option), "") },
                label = { Text(text = getLabel(option)) }
            )
        }
    }
}

private fun getIcon(option: NavigationOption): ImageVector =
    when (option) {
        NavigationOption.PIZZAS -> Icons.Default.Add
        NavigationOption.ORDERS -> Icons.Default.Menu
        NavigationOption.CART -> Icons.Default.ShoppingCart
        NavigationOption.PROFILE -> Icons.Default.AccountCircle
    }

@Composable
private fun getLabel(option: NavigationOption): String = stringResource(
    when (option) {
        NavigationOption.PIZZAS -> R.string.bottom_bar_pizzas
        NavigationOption.ORDERS -> R.string.bottom_bar_orders
        NavigationOption.PROFILE -> R.string.bottom_bar_profile
        NavigationOption.CART -> R.string.bottom_bar_cart
    }
)

fun NavController.openPoppingAllPrevious(route: Any) {
    this.navigate(route) {
        popUpTo(graph.startDestinationId)
        launchSingleTop = true
    }
}