package com.devhjs.memo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devhjs.memo.presentation.home.HomeScreen
import com.devhjs.memo.presentation.info.InformationScreenRoot
import com.devhjs.memo.presentation.shopping.ShoppingScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ShoppingList : Screen("shopping_list")
    object UserInfo : Screen("user_info")
}

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
            ShoppingScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.UserInfo.route) {
            InformationScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
