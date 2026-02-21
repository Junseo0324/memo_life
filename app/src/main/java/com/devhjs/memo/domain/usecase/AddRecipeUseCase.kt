package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.Recipe
import com.devhjs.memo.domain.repository.RecipeRepository
import javax.inject.Inject

class AddRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: Recipe) {
        repository.insertRecipe(recipe)
    }
}
