package com.example.pizza_shift_intensive.presentation.mappers

import com.example.pizza_shift_intensive.domain.model.ComponentModel
import com.example.pizza_shift_intensive.domain.model.DoughOptionModel
import com.example.pizza_shift_intensive.domain.model.SizeOptionModel
import com.example.pizza_shift_intensive.presentation.model.ComponentUiModel
import com.example.pizza_shift_intensive.presentation.model.DoughUiModel
import com.example.pizza_shift_intensive.presentation.model.SizesUiModel

fun ComponentModel.toUiModel(): ComponentUiModel {
    return ComponentUiModel(
        type = typeToString(this.type),
        img = this.img,
        price = this.price
    )
}

fun DoughOptionModel.toUiModel(): DoughUiModel {
    return DoughUiModel(
        type = typeToString(this.type),
        price = this.price
    )
}

fun SizeOptionModel.toUiModel(): SizesUiModel {
    return SizesUiModel(
        type = typeToString(this.type),
        price = this.price,
        diameter = formatDiameter(this.type)
    )
}