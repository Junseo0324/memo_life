package com.devhjs.memo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devhjs.memo.presentation.home.HomeScreen
import com.devhjs.memo.presentation.info.InformationScreenRoot
import com.devhjs.memo.presentation.recipe.RecipeScreenRoot
import com.devhjs.memo.presentation.recipe.RecipeWriteScreenRoot
import com.devhjs.memo.presentation.shopping.ShoppingScreenRoot


@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onMenuClick = { route ->
                    navController.navigate(route)
                }
            )
        }
        composable(Screen.ShoppingList.route) {
            ShoppingScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Info.route) {
            InformationScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Recipe.route) {
            RecipeScreenRoot(
                onBackClick = { navController.popBackStack() },
                onNewRecipeClick = { navController.navigate(Screen.RecipeWrite.route) }
            )
        }

        composable(Screen.RecipeWrite.route) {
            RecipeWriteScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
