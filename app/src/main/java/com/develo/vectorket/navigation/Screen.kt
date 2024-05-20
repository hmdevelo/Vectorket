package com.develo.vectorket.navigation

sealed class Screen(val route: String) {
    data object LoginScreen : Screen("login_screen")
    data object MainScreen : Screen("main_screen")
    data object HomeScreen : Screen("home_screen")
    data object SearchScreen : Screen("search_screen")
    data object FeedsScreen : Screen("feeds_screen")
    data object ProfileScreen : Screen("profile_screen")
    data object ReelScreen : Screen("reel_screen")
    data object BlogScreen : Screen("blog_screen")
}