package com.example.pizza_shift_intensive.data.mock

import com.example.pizza_shift_intensive.domain.model.ComponentModel
import com.example.pizza_shift_intensive.domain.model.DoughOptionModel
import com.example.pizza_shift_intensive.domain.model.PizzaModel
import com.example.pizza_shift_intensive.domain.model.SizeOptionModel
import com.example.pizza_shift_intensive.domain.model.TypeComponentModel
import com.example.pizza_shift_intensive.domain.model.TypeDoughModel
import com.example.pizza_shift_intensive.domain.model.TypeSizeModel


// Моковые данные для тестирования
val pizzaModelItems = listOf(
    PizzaModel(
        id = "1",
        name = "Маргарита",
        ingredients = listOf(
            ComponentModel(
                type = TypeComponentModel.CHILE,
                price = 30,
                img = "https://example.com/images/tomato_sauce.png"
            ),
            ComponentModel(
                type = TypeComponentModel.MOZZARELLA,
                price = 70,
                img = "https://example.com/images/mozzarella.png"
            )
        ),
        toppings = listOf(
            ComponentModel(
                type = TypeComponentModel.BASIL,
                price = 20,
                img = "https://example.com/images/basil.png"
            )
        ),
        description = "Классическая итальянская пицца с томатным соусом и сыром моцарелла.",
        sizes = listOf(
            SizeOptionModel(
                type = TypeSizeModel.SMALL,
                price = 345,
            ),
            SizeOptionModel(
                type = TypeSizeModel.MEDIUM,
                price = 658,
            ),
            SizeOptionModel(
                type = TypeSizeModel.LARGE,
                price = 890,
            )
        ),
        doughs = listOf(
            DoughOptionModel(
                type = TypeDoughModel.THIN,
                price = 0,
            ),
            DoughOptionModel(
                type = TypeDoughModel.THICK,
                price = 50,
            )
        ),
        calories = 750,
        protein = "28g",
        totalFat = "22g",
        carbohydrates = "98g",
        sodium = "1.1g",
        allergens = listOf("Gluten", "Dairy"),
        isVegetarian = true,
        isGlutenFree = false,
        isNew = false,
        isHit = true,
        img = "https://example.com/images/margherita.png"
    ),

    PizzaModel(
        id = "2",
        name = "Пепперони",
        ingredients = listOf(
            ComponentModel(
                type = TypeComponentModel.BACON,
                price = 50,
                img = "https://example.com/images/tomato_sauce.png"
            ),
            ComponentModel(
                type = TypeComponentModel.PEPERONI,
                price = 40,
                img = "https://example.com/images/mozzarella.png"
            ),
            ComponentModel(
                type = TypeComponentModel.GREEN_PEPPER,
                price = 60,
                img = "https://example.com/images/pepperoni.png"
            )
        ),
        toppings = listOf(
            ComponentModel(
                type = TypeComponentModel.CHILE,
                price = 70,
                img = "https://example.com/images/chili_flakes.png"
            )
        ),
        description = "Острая пицца с тонким тестом, моцареллой и пепперони.",
        sizes = listOf(
            SizeOptionModel(
                type = TypeSizeModel.SMALL,
                price = 324,
            ),
            SizeOptionModel(
                type = TypeSizeModel.MEDIUM,
                price = 456,
            ),
            SizeOptionModel(
                type = TypeSizeModel.LARGE,
                price = 679,
            )
        ),
        doughs = listOf(
            DoughOptionModel(
                type = TypeDoughModel.THIN,
                price = 0,
            ),
            DoughOptionModel(
                type = TypeDoughModel.THICK,
                price = 50,
            )
        ),
        calories = 820,
        protein = "32g",
        totalFat = "28g",
        carbohydrates = " ninety-five grams",
        sodium = "1.3g",
        allergens = listOf("Gluten", "Dairy"),
        isVegetarian = false,
        isGlutenFree = false,
        isNew = false,
        isHit = true,
        img = "https://example.com/images/pepperoni.png"
    ),

    PizzaModel(
        id = "3",
        name = "Веганская",
        ingredients = listOf(
            ComponentModel(
                type = TypeComponentModel.BASIL,
                price = 10,
                img = "https://example.com/images/dough.png"
            ),
            ComponentModel(
                type = TypeComponentModel.GREEN_PEPPER,
                price = 20,
                img = "https://example.com/images/tomato_sauce.png"
            ),
            ComponentModel(
                type = TypeComponentModel.FETA,
                price = 30,
                img = "https://example.com/images/vegan_cheese.png"
            ),
            ComponentModel(
                type = TypeComponentModel.HAM,
                price = 40,
                img = "https://example.com/images/grilled_veggies.png"
            )
        ),
        toppings = listOf(
            ComponentModel(
                type = TypeComponentModel.ONION,
                price = 56,
                img = "https://example.com/images/arugula.png"
            )
        ),
        description = "Лёгкая пицца на растительном сыре с обжаренными овощами.",
        sizes = listOf(
            SizeOptionModel(
                type = TypeSizeModel.SMALL,
                price = 234,
            ),
            SizeOptionModel(
                type = TypeSizeModel.MEDIUM,
                price = 453,
            ),
            SizeOptionModel(
                type = TypeSizeModel.LARGE,
                price = 567,
            )
        ),
        doughs = listOf(
            DoughOptionModel(
                type = TypeDoughModel.THIN,
                price = 0,
            ),
            DoughOptionModel(
                type = TypeDoughModel.THICK,
                price = 50,
            )
        ),
        calories = 680,
        protein = "14g",
        totalFat = "18g",
        carbohydrates = " ninety-two grams",
        sodium = "1.0g",
        allergens = listOf("Soy"),
        isVegetarian = true,
        isGlutenFree = true,
        isNew = true,
        isHit = false,
        img = "https://example.com/images/vegan.png"
    )
)


fun getPizzasList(): List<PizzaModel> {
    return pizzaModelItems
}

fun getPizzaById(pizzaId: String): PizzaModel? {
    return pizzaModelItems.find { it.id == pizzaId }
}
