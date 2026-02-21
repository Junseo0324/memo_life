package com.devhjs.memo.domain.usecase

import com.devhjs.memo.domain.model.Recipe
import com.devhjs.memo.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return repository.getAllRecipes()
    }
}
