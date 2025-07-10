package com.example.pizza_shift_intensive.presentation.ui.navigation

import com.example.pizza_shift_intensive.presentation.ui.screens.cart.CartRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.orders.OrdersRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.pizzalist.PizzaListRoute
import com.example.pizza_shift_intensive.presentation.ui.screens.profile.ProfileRoute
import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    PIZZAS(PizzaListRoute::class),
    ORDERS(OrdersRoute::class),
    CART(CartRoute::class),
    PROFILE(ProfileRoute::class);
}