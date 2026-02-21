package com.devhjs.memo.presentation.recipe

sealed interface RecipeEvent {
    data object NavigateBack : RecipeEvent
    data object NavigateToWrite : RecipeEvent
    data class NavigateToDetail(val id: Int) : RecipeEvent
    data class ShowSnackbar(val message: String) : RecipeEvent
}
