package com.devhjs.memo.data.repository

import com.devhjs.memo.data.local.dao.RecipeDao
import com.devhjs.memo.data.mapper.toRecipe
import com.devhjs.memo.data.mapper.toRecipeEntity
import com.devhjs.memo.domain.model.Recipe
import com.devhjs.memo.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dao: RecipeDao
) : RecipeRepository {
    override fun getAllRecipes(): Flow<List<Recipe>> {
        return dao.getAllRecipes().map { entities ->
            entities.map { it.toRecipe() }
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return dao.getRecipeById(id)?.toRecipe()
    }

    override suspend fun insertRecipe(recipe: Recipe) {
        dao.insertRecipe(recipe.toRecipeEntity())
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        dao.updateRecipe(recipe.toRecipeEntity())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        dao.deleteRecipe(recipe.toRecipeEntity())
    }
}
