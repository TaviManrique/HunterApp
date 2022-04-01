package com.manriquetavi.hunterapp.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object Details: Screen("details_screen/{hunterId}") {
        fun passHunterId(hunterId: Int): String {
            return "details_screen/$hunterId"
        }
    }
    object Search: Screen("search_screen")
}
