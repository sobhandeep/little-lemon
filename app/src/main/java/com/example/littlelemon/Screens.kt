package com.example.littlelemon

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"
const val ONBOARDING_ROUTE = "onboard"
const val PROFILE_ROUTE = "profile"

sealed class Screen(
    val route: String,
    val title: String
){
    data object Home: Screen(
        route = "home_screen",
        title = "Home Screen"
    )
    data object Onboard: Screen(
        route = "onboarding_screen",
        title = "Onboarding Screen"
    )
    data object Profile: Screen(
        route = "profile_screen",
        title = "Profile Screen"
    )
}