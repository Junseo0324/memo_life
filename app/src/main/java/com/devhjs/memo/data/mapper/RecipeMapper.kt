package com.devhjs.memo.data.mapper

import com.devhjs.memo.data.local.entity.RecipeEntity
import com.devhjs.memo.domain.model.Recipe

fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = id,
        title = title,
        ingredients = ingredients,
        instructions = instructions,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}

fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        id = id ?: 0,
        title = title,
        ingredients = ingredients,
        instructions = instructions,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}
