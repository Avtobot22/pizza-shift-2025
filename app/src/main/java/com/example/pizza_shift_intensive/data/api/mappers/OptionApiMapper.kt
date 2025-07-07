package com.example.pizza_shift_intensive.data.api.mappers

import com.example.pizza_shift_intensive.data.api.model.ComponentApiModel
import com.example.pizza_shift_intensive.data.api.model.OptionApiModel
import com.example.pizza_shift_intensive.domain.model.ComponentModel
import com.example.pizza_shift_intensive.domain.model.DoughOptionModel
import com.example.pizza_shift_intensive.domain.model.SizeOptionModel
import com.example.pizza_shift_intensive.domain.model.TypeComponentModel
import com.example.pizza_shift_intensive.domain.model.TypeDoughModel
import com.example.pizza_shift_intensive.domain.model.TypeSizeModel

fun ComponentApiModel.toComponentModel(): ComponentModel {
    return ComponentModel(
        type = TypeComponentModel.valueOf(type),
        price = price,
        img = formatImageUrl(img)
    )
}

fun OptionApiModel.toSizeOptionModel(): SizeOptionModel {
    return SizeOptionModel(
        type = TypeSizeModel.valueOf(type),
        price = price,
    )
}

fun OptionApiModel.toDoughOptionModel(): DoughOptionModel {
    return DoughOptionModel(
        type = TypeDoughModel.valueOf(type),
        price = price,
    )
}