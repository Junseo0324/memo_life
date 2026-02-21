package com.devhjs.memo.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ShoppingList : Screen("shopping_list")
    object Info : Screen("info")
    object Recipe : Screen("recipe")
    object RecipeWrite : Screen("recipe_write")
    object RecipeDetail : Screen("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe_detail/$recipeId"
    }
}