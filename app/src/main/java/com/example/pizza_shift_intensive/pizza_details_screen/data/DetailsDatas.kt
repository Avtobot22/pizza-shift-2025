package com.example.pizza_shift_intensive.pizza_details_screen.data

import com.example.pizza_shift_intensive.R

object DetailsData {
    val pizzaDetailsItems = listOf<PizzaDetailsItem>(
        PizzaDetailsItem(
            id = 1,
            name = "Пепперони",
            basePrice = 399,
            imageResId = R.drawable.pepperoni_pizza,
            ingredients = listOf("Пепперони", "Моцарелла", "Томатный соус")
        ),
        PizzaDetailsItem(
            id = 2,
            name = "Сырная пицца",
            basePrice = 389,
            imageResId = R.drawable.cheese_pizza,
            ingredients = listOf("Моцарелла", "Чеддер", "Пармезан", "Соус Альфредо")
        ),
        PizzaDetailsItem(
            id = 3,
            name = "Двойной цыпленок",
            basePrice = 479,
            imageResId = R.drawable.double_chick_pizza,
            ingredients = listOf("Цыпленок", "Моцарелла", "Соус Альфредо")
        ),
        PizzaDetailsItem(
            id = 4,
            name = "Ветчина и сыр",
            basePrice = 389,
            imageResId = R.drawable.ham_and_cheese_pizza,
            ingredients = listOf("Ветчина", "Моцарелла", "Томатный соус")
        )
    )
}

fun getPizzaDetailsById(id: Long): PizzaDetailsItem? {
    return DetailsData.pizzaDetailsItems.find { it.id == id }
}
