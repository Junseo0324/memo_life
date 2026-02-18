package com.devhjs.memo.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devhjs.memo.presentation.home.HomeScreen
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
            PlaceholderScreen(title = "회원가입 정보 화면")
        }
    }
}

@Composable
fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title)
    }
}
