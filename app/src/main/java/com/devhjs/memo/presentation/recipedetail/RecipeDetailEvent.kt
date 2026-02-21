package com.devhjs.memo.presentation.recipedetail


sealed interface RecipeDetailEvent {
    object NavigateBack : RecipeDetailEvent
}
