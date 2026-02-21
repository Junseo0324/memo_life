package com.devhjs.memo.presentation.recipewrite

import androidx.compose.runtime.Immutable

@Immutable
data class RecipeWriteState(
    val title: String = "",
    val imageUrl: String? = null,
    val ingredients: List<String> = listOf(""),
    val instructions: List<String> = listOf(""),
    val isLoading: Boolean = false
)
