package com.example.pizza_shift_intensive.pizza_details_screen.data

data class PizzaDetailsItem (
    val id: Long,
    val name: String,
    val basePrice: Long,
    val imageResId: Int,
    val ingredients: List<String>,
    val selectedSize: PizzasPrices = PizzasPrices.SMALL,
) {
    val finalPrice: Long
        get() = basePrice + selectedSize.priceModifier
}