package com.devhjs.memo.presentation.recipedetail

import androidx.compose.runtime.Immutable
import com.devhjs.memo.domain.model.Recipe

@Immutable
data class RecipeDetailState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false
)

