package com.example.pizza_shift_intensive.pizza_list_screen.data

import com.example.pizza_shift_intensive.R

object PizzaDataSource {
    val pizzaItems = listOf<PizzaListItem>(
        PizzaListItem(
            id = 1,
            name = "Пепперони",
            description = "Пикантная пепперони," +
                    " увеличенная порция моцареллы, " +
                    "фирменный томатный соус",
            price = 399,
            imageResId = R.drawable.pepperoni_pizza
        ),
        PizzaListItem(
            id = 2,
            name = "Сырная пицца",
            description = "Моцарелла, " +
                    "сыры чеддер и пармезан, " +
                    "фирменный соус альфредо",
            price = 389,
            imageResId = R.drawable.cheese_pizza
        ),
        PizzaListItem(
            id = 3,
            name = "Двойной цыпленок",
            description = "Цыпленок, " +
                    "моцарелла, фирменный соус альфредо",
            price = 479,
            imageResId = R.drawable.double_chick_pizza
        ),
        PizzaListItem(
            id = 4,
            name = "Ветчина и сыр",
            description = "Ветчина, " +
                    "моцарелла, фирменный томатный соус",
            price = 389,
            imageResId = R.drawable.ham_and_cheese_pizza
        ),
    )
}