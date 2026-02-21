package com.devhjs.memo.domain.model

data class Recipe(
    val id: Int? = null,
    val title: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val imageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
