package com.devhjs.memo.presentation.recipe

import androidx.compose.runtime.Immutable

@Immutable
data class RecipeState(
    val recipes: List<RecipeItemUiModel> = emptyList()
)

data class RecipeItemUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String? = null
)
