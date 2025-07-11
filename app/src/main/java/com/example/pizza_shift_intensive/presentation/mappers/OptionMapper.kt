package com.example.pizza_shift_intensive.presentation.mappers

import com.example.pizza_shift_intensive.domain.model.ComponentModel
import com.example.pizza_shift_intensive.domain.model.DoughOptionModel
import com.example.pizza_shift_intensive.domain.model.SizeOptionModel
import com.example.pizza_shift_intensive.presentation.model.ComponentUiModel
import com.example.pizza_shift_intensive.presentation.model.DoughUiModel
import com.example.pizza_shift_intensive.presentation.model.SizesUiModel

fun ComponentModel.toUiModel(formatComponent: (String) -> String = { it }) =
    ComponentUiModel(
        type = formatComponent(typeToString(this.type)),
        img = this.img,
        price = this.price
    )


fun DoughOptionModel.toUiModel() =
    DoughUiModel(
        type = typeToString(this.type),
        price = this.price
    )

fun SizeOptionModel.toUiModel() =
    SizesUiModel(
        type = typeToString(this.type),
        price = this.price,
        diameter = formatDiameter(this.type)
    )