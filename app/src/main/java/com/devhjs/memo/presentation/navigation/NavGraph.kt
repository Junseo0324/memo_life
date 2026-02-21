package com.devhjs.memo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devhjs.memo.presentation.home.HomeScreen
import com.devhjs.memo.presentation.info.InformationScreenRoot
import com.devhjs.memo.presentation.recipe.RecipeScreenRoot
import com.devhjs.memo.presentation.recipedetail.RecipeDetailScreenRoot
import com.devhjs.memo.presentation.recipewrite.RecipeWriteScreenRoot
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
                onNewRecipeClick = { navController.navigate(Screen.RecipeWrite.route) },
                onRecipeClick = { id ->
                    navController.navigate(Screen.RecipeDetail.createRoute(id))
                }
            )
        }

        composable(Screen.RecipeWrite.route) {
            RecipeWriteScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(
                navArgument("recipeId") { type = NavType.StringType }
            )
        ) {
            RecipeDetailScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
