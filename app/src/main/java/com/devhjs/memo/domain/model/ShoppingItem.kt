package com.devhjs.memo.domain.model

data class ShoppingItem(
    val id: Int = 0,
    val name: String,
    val quantity: String,
    val isChecked: Boolean = false
)
