package com.devhjs.memo.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ShoppingList : Screen("shopping_list")
    object Info : Screen("info")
}