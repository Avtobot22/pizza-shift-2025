package com.example.pizza_shift_intensive.presentation.mappers

import com.example.pizza_shift_intensive.domain.model.TypeComponentModel
import com.example.pizza_shift_intensive.domain.model.TypeDoughModel
import com.example.pizza_shift_intensive.domain.model.TypeSizeModel

fun typeToString(type: TypeComponentModel): String {
    return when (type) {
        TypeComponentModel.HAM -> "ветчина"
        TypeComponentModel.MUSHROOMS -> "грибы"
        TypeComponentModel.FETA -> "сыр фета"
        TypeComponentModel.GREEN_PEPPER -> "зеленый перец"
        TypeComponentModel.MOZZARELLA -> "моцарелла"
        TypeComponentModel.BACON -> "бекон"
        TypeComponentModel.BASIL -> "базилик"
        TypeComponentModel.CHILE -> "чили"
        TypeComponentModel.ONION -> "лук"
        TypeComponentModel.TOMATO -> "помидоры"
        TypeComponentModel.CHEDDAR -> "сыр чеддер"
        TypeComponentModel.PEPERONI -> "пепперони"
        TypeComponentModel.SHRIMPS -> "креветки"
        TypeComponentModel.PICKLE -> "огурцы"
        TypeComponentModel.PARMESAN -> "сыр пармезан"
        TypeComponentModel.MEATBALLS -> "митболы"
        TypeComponentModel.PINEAPPLE -> "ананас"
        TypeComponentModel.CHICKEN_FILLET -> "куриное филе"
    }
}

fun typeToString(type: TypeDoughModel): String {
    return when (type) {
        TypeDoughModel.THIN -> "тонкое"
        TypeDoughModel.THICK -> "традиционное"
    }
}

fun typeToString(type: TypeSizeModel): String {
    return when (type) {
        TypeSizeModel.SMALL -> "маленькая"
        TypeSizeModel.MEDIUM -> "средняя"
        TypeSizeModel.LARGE -> "большая"
    }
}

fun formatDiameter(type: TypeSizeModel): Int {
    return when (type) {
        TypeSizeModel.SMALL -> 25
        TypeSizeModel.MEDIUM -> 30
        TypeSizeModel.LARGE -> 35
    }
}
fun formatFirstLetter(text: String): String {
    return text.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}