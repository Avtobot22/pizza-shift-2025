package com.example.pizza_shift_intensive.pizza_details_screen.data

enum class PizzasPrices (val displayName: String, val priceModifier: Long, val diameter: Int) {
    SMALL("Маленькая", 0L, 25),
    MEDIUM("Средняя", 150L, 30),
    LARGE("Большая", 300L, 35);
}