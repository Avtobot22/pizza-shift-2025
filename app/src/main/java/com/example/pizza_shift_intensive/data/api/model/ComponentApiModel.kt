package com.example.pizza_shift_intensive.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ComponentApiModel(
    val type: String,
    val price: Int,
    val img: String
)