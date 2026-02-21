package com.devhjs.memo.presentation.recipe

sealed interface RecipeAction {
    data object OnBackClick : RecipeAction
    data object OnNewRecipeClick : RecipeAction
    data class OnRecipeClick(val id: Int) : RecipeAction
}
