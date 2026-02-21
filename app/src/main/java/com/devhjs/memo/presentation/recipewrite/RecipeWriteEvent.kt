package com.devhjs.memo.presentation.recipewrite

sealed interface RecipeWriteEvent {
    data object SaveSuccess : RecipeWriteEvent
    data object NavigateBack : RecipeWriteEvent
    data class ShowSnackbar(val message: String) : RecipeWriteEvent
}
