package com.example.littlelemon.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.littlelemon.HOME_ROUTE
import com.example.littlelemon.Screen
import com.example.littlelemon.screens.HomeScreen

fun NavGraphBuilder.homeNav(navController: NavHostController){
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ){
        composable(route = Screen.Onboard.route) {
            HomeScreen(navController)
        }
    }
}