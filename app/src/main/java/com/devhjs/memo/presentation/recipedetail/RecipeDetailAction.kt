package com.devhjs.memo.presentation.recipedetail


sealed interface RecipeDetailAction {
    object OnBackClick : RecipeDetailAction
}