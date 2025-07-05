package com.example.pizza_shift_intensive.domain.model

data class PizzaModel(
    val id: String,
    val name: String,
    val ingredients: List<ComponentModel>,
    val toppings: List<ComponentModel>,
    val description: String,
    val sizes: List<SizeOptionModel>,
    val doughs: List<DoughOptionModel>,
    val calories: Int,
    val protein: String,
    val totalFat: String,
    val carbohydrates: String,
    val sodium: String,
    val allergens: List<String>,
    val isVegetarian: Boolean,
    val isGlutenFree: Boolean,
    val isNew: Boolean,
    val isHit: Boolean,
    val img: String
)