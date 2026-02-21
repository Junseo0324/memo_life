package com.devhjs.memo.presentation.recipewrite

sealed interface RecipeWriteAction {
    data class UpdateTitle(val title: String) : RecipeWriteAction
    data class UpdateImage(val uri: String?) : RecipeWriteAction
    data object AddIngredient : RecipeWriteAction
    data class UpdateIngredient(val index: Int, val text: String) : RecipeWriteAction
    data class RemoveIngredient(val index: Int) : RecipeWriteAction
    data object AddInstruction : RecipeWriteAction
    data class UpdateInstruction(val index: Int, val text: String) : RecipeWriteAction
    data class RemoveInstruction(val index: Int) : RecipeWriteAction
    data object SaveRecipe : RecipeWriteAction
    data object OnBackClick : RecipeWriteAction
}
